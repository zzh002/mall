package com.mall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ZZH
 * @date 2018/10/2 0002 17:48
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "alipay")
@Component
public class AlipayConfig {
    private String callbackurl;

}
