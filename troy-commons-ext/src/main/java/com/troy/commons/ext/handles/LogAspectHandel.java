package com.troy.commons.ext.handles;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.troy.commons.constraints.Log;
import com.troy.commons.utils.IdGen;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 记录日志
 *
 * @author ydp
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class LogAspectHandel {

    /**
     * 同一线程中的同一方法签名记录
     */
    ThreadLocal<Map<String, Map<String, Object>>> logMap = new ThreadLocal();

    /**
     * 在所有标注@Log的地方切入
     *
     * @param joinPoint
     */
    @Before("@annotation(lg)")
    public void beforeExec(JoinPoint joinPoint, Log lg) {
        // before invoke method
        if (logMap.get() == null || logMap.get().isEmpty()) {
            Map<String, Map<String, Object>> map = Maps.newHashMap();
            logMap.set(map);
        }
        // 对同一线程中的多个切面支持
        Map<String, Object> innerMap = Maps.newHashMap();
        // 方法签名（以此作为主键）
        logMap.get().put(joinPoint.getSignature().toShortString(), innerMap);
        innerMap.put("startTime", System.currentTimeMillis());
        innerMap.put("traceId", IdGen.uuid());

        // print log
        info(joinPoint, lg.inputPrint());
    }

    @After("@annotation(lg)")
    public void afterExec(JoinPoint joinPoint, Log lg) {
        Map<String, Object> map = logMap.get().get(joinPoint.getSignature().toShortString());
        long executionTime = System.currentTimeMillis() - (Long) map.get("startTime");
        map.put("executionTime", executionTime);
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        logMap.remove();
        log.info(method.getAnnotation(Log.class).value() + " traceId = [" + map.get("traceId") + "]. methodName = [" + joinPoint.getSignature().toShortString() + "]. Execution time : ["
                + executionTime + "] ms");
    }

    @AfterReturning(pointcut = "@annotation(lg)", returning = "result")
    public void afterReturningExec(JoinPoint joinPoint, Log lg, Object result) throws Throwable {
        if (lg.outputPrint()) {
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            Method method = ms.getMethod();
            log.info(method.getAnnotation(Log.class).value() + " 执行结束，返回内容：" + JSONObject.toJSONString(result));
            log.info("--------------------------------------------------");
        }
    }

    @AfterThrowing(pointcut = "@annotation(lg)", throwing = "e")
    public void afterThrowingExec(JoinPoint joinPoint, Log lg, Exception e) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        log.info(method.getAnnotation(Log.class).value() + " 执行异常，进入异常信息记录流程");
        log.info(JSONObject.toJSONString(logMap.get()));
        log.info("--------------------------------------------------");
//		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
//		e.printStackTrace(new java.io.PrintWriter(buf, true));
//		String expMessage = buf.toString();
//		System.out.println(expMessage);
//		buf.close();
//		id
//		type		结果（0-失败，1-成功）
//		errorMsg	异常
//		successResult	正常返回内容
//		startTime	起始时间
//		executionTime	耗时
//		ip		ip
//		args		参数
//		uri		调用uri
//		signature	方法签名
//		traceId		跟踪ID
//		httpStatus
    }

    /**
     * 打印controller被调用信息
     *
     * @param joinPoint
     * @param inputPrint
     */
    private void info(JoinPoint joinPoint, boolean inputPrint) {
        List<String> argList = new ArrayList();

        HttpServletRequest request = null;
        Object[] args = joinPoint.getArgs();
        int length = args == null ? 0 : args.length;
        // 遍历controller接收的参数
        for (int i = 0; i < length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }
        Map<String, Object> innerMap = logMap.get().get(joinPoint.getSignature().toShortString());
        String ip = "";
        String uri = "";
        if (request != null) {
            ip = getIp(request);
            uri = request.getRequestURI();
            request.setAttribute("traceId", innerMap.get("traceId"));
        }

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();

        log.info(method.getAnnotation(Log.class).value() + " traceId = [" + innerMap.get("traceId") + "] 被调用");
        if (log.isInfoEnabled() && inputPrint) {
            log.info("--------------------------------------------------");
//			log.info("Kind:\t" + joinPoint.getKind());
//			log.info("Target:\t" + joinPoint.getTarget().toString());
            log.info("RequestURI:\t {}", uri);
            String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            try {
                Object[] objects = joinPoint.getArgs();
                log.info("Args:");
                for (int i = 0; i < objects.length; i++) {
//					log.info("\t==>arg[" + i + "]:\t" + os[i].toString());
                    log.info("\t==>" + paramNames[i] + ":\t" + String.valueOf(objects[i]));
                    argList.add(String.valueOf(objects[i]));
                }
            } catch (Exception e) {
                log.info("出现不被兼容的参数，跳过打印参数日志");
            }
            log.info("Signature:\t {}", joinPoint.getSignature());
//			log.info("SourceLocation:\t" + joinPoint.getSourceLocation());
//			log.info("StaticPart:\t" + joinPoint.getStaticPart());
            log.info("Ip:\t {}", ip);
            log.info("--------------------------------------------------");
        }
        innerMap.put("signature", joinPoint.getSignature().toString());
        innerMap.put("argList", JSONObject.toJSONString(argList));
        innerMap.put("ip", ip);
        innerMap.put("uri", uri);
    }

    private final static String UNKOWN = "unknown";

    private final static String COMMA = ",";

    /**
     * 获取调用客户端的真实IP
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(COMMA)) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

}
