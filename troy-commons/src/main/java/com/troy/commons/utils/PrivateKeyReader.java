package com.troy.commons.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 公私钥处理
 * @author dp
 */
public class PrivateKeyReader {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获得私钥
     * @param fileName
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String fileName)throws Exception {
        byte[] keyBytes = getBytes(fileName);
        PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * 获得公钥
     * @param fileName
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String fileName) throws Exception {
        byte[] keyBytes = getBytes(fileName);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * 使用公钥加密
     *
     * @param content         待加密内容
     * @param fileName        公钥路径
     * @return 经过 base64 编码后的字符串
     */
    public static String encipher(String content, String fileName) throws Exception {
        return encipher(content, getPublicKey(fileName), MAX_ENCRYPT_BLOCK);
    }

    /**
     * 使用公钥加密
     *
     * @param content         待加密内容
     * @param publicKey       公钥
     * @return 经过 base64 编码后的字符串
     */
    public static String encipher(String content, PublicKey publicKey) {
        return encipher(content, publicKey, MAX_ENCRYPT_BLOCK);
    }

    /**
     * 使用私钥解密
     *
     * @param contentBase64    待加密内容,base64 编码
     * @param fileName         私钥路径
     * @return
     * @segmentSize 分段大小
     */
    public static String decipher(String contentBase64, String fileName) throws Exception {
        return decipher(contentBase64, getPrivateKey(fileName), MAX_DECRYPT_BLOCK);
    }

    /**
     * 使用私钥解密
     *
     * @param contentBase64    待加密内容,base64 编码
     * @param privateKey       私钥
     * @return
     * @segmentSize 分段大小
     */
    public static String decipher(String contentBase64, PrivateKey privateKey) {
        return decipher(contentBase64, privateKey, MAX_DECRYPT_BLOCK);
    }

    /**
     * 通过文件获取文件中字节
     * @param filename
     * @return
     * @throws IOException
     */
    private static byte[] getBytes(String filename) throws IOException {
        FileInputStream fis = null;
        DataInputStream dis = null;
        byte[] keyBytes;
        try {
            File f = new File(filename);
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            keyBytes = new byte[(int)f.length()];
            dis.readFully(keyBytes);
        } finally {
            if(dis != null){
                dis.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
        return keyBytes;
    }

    /**
     * 分段加密
     *
     * @param ciphertext  密文
     * @param key         加密秘钥
     * @param segmentSize 分段大小，<=0 不分段
     * @return
     */
    private static String encipher(String ciphertext, java.security.Key key, int segmentSize) {
        try {
            // 用公钥加密
            byte[] srcBytes = ciphertext.getBytes();

            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resultBytes = null;

            if (segmentSize > 0) {
                resultBytes = cipherDoFinal(cipher, srcBytes, segmentSize); //分段加密
            } else {
                resultBytes = cipher.doFinal(srcBytes);
            }
            String base64Str = (new BASE64Encoder()).encodeBuffer(resultBytes);
            return base64Str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分段大小
     *
     * @param cipher
     * @param srcBytes
     * @param segmentSize
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    private static byte[] cipherDoFinal(Cipher cipher, byte[] srcBytes, int segmentSize)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        if (segmentSize <= 0) {
            throw new RuntimeException("分段大小必须大于0");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int inputLen = srcBytes.length;
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > segmentSize) {
                cache = cipher.doFinal(srcBytes, offSet, segmentSize);
            } else {
                cache = cipher.doFinal(srcBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * segmentSize;
        }
        byte[] data = out.toByteArray();
        out.close();
        return data;
    }

    /**
     * 分段解密
     *
     * @param contentBase64 密文
     * @param key           解密秘钥
     * @param segmentSize   分段大小（小于等于0不分段）
     * @return
     */
    private static String decipher(String contentBase64, java.security.Key key, int segmentSize) {
        try {
            // 用私钥解密
            byte[] srcBytes = (new BASE64Decoder()).decodeBuffer(contentBase64);
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher deCipher = Cipher.getInstance("RSA");
            // 根据公钥，对Cipher对象进行初始化
            deCipher.init(Cipher.DECRYPT_MODE, key);
            // deCipher.doFinal(srcBytes);
            byte[] decBytes;
            if (segmentSize > 0) {
                //分段解密
                decBytes = cipherDoFinal(deCipher, srcBytes, segmentSize);
            } else {
                decBytes = deCipher.doFinal(srcBytes);
            }

            String decrytStr = new String(decBytes);
            return decrytStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试类
     * @throws Exception
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    private static void run() throws Exception {

//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());
//        keyPairGenerator.initialize(1024, secureRandom);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//        String publicKeyFilename = "/Users/ydp/Downloads/publicKeyFile";
//        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
//        fos.write(publicKeyBytes);
//        fos.close();
//        String privateKeyFilename = "/Users/ydp/Downloads/privateKeyFile";
//        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//        fos = new FileOutputStream(privateKeyFilename);
//        fos.write(privateKeyBytes);
//        fos.close();

        String planSecret = "remove|book|order|organ|muscle|squeeze|enough|airport|exercise|level|fiction|matrix|load|snack|oppose|junior|ketchup|pill|charge|frequent|trash|cart|where|able";
        System.out.println("加密前：" + planSecret);
        String secret = PrivateKeyReader.encipher(planSecret, PrivateKeyReader.getPublicKey("/Users/ydp/Downloads/dex秘钥/publicKeyFile_dex"));
        System.out.println("加密后：" + secret);
        System.out.println("解密后：" + PrivateKeyReader.decipher(secret, PrivateKeyReader.getPrivateKey("/Users/ydp/Downloads/dex秘钥/privateKeyFile_dex")));
//        System.out.println("解密后：" + PrivateKeyReader.decipher(
//                "oudioQVjw5lVhe9Q5jNGAVRfcK/qlL4og9q0tSs/0PRPjrLF9D3D2f+QpdaPJjmNwxwXY6Rll9y2\n" +
//                        "+VcgoBuGWWnR0EucRYLJAGDNcu500i+CdYh5vkvvxThLBQMOFZsUtLZYYm4HACdJYbFUcyeS6i9+\n" +
//                        "/6480JXRIC0JhOTEKsw=", PrivateKeyReader.getPrivateKey("/Users/ydp/Documents/privateKeyFile")));
    }

    public static void main(String[] args) throws Exception {
        run();
    }

}
