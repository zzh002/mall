package com.mall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZZH
 * @date 2018/10/2 0002 17:35
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "ftp")
@Component
public class FTPConfig {
    private String ip;

    private String user;

    private String password;

    private String prefix;

    private int port;
}
