package com.hubproductsmanagement.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = UserConfig.PREFIX)
@Configuration
public class UserConfig {
    public static final String PREFIX = "props.authorization";
    private String userStaff;
    private String passStaff;
    private String userAdmin;
    private String passAdmin;

    private String roleStaff;
    private String roleAdmin;
}
