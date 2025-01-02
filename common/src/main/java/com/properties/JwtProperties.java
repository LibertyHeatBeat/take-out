package com.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "bk.jwt")
@Data
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */

    private String adminSecretKey = getSecretKey();
    private long adminTtl;
    private String adminTokenName;

    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    private String userSecretKey = getSecretKey();;
    private long userTtl;
    private String userTokenName;

    public String getSecretKey() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int desiredLength = 5; // 你想要的长度
        String truncatedUuid = uuid.substring(0, Math.min(desiredLength, uuid.length()));
        return truncatedUuid;
    }
}
