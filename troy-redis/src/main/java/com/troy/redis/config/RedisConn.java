package com.troy.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis连接
 *
 * @author troy
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConn {

    private String host;

    private int port;

    private int timeout;

    private String password;

    private int database;

}
