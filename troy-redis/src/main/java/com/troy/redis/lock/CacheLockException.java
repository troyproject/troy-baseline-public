package com.troy.redis.lock;

/**
 * CacheLockException
 *
 * @author troy
 */
public class CacheLockException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CacheLockException() {
    }

    public CacheLockException(Throwable cause) {
        super(cause);
    }

    public CacheLockException(String message) {
        super(message);
    }

    public CacheLockException(String message, Throwable cause) {
        super(message, cause);
    }

}
