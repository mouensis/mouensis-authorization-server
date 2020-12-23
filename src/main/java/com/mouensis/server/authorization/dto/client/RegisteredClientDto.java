package com.mouensis.server.authorization.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志实体类
 *
 * @author zhuyuan
 * @date 2020/12/9 23:52
 */
@Getter
@Setter
@ToString
@Schema(title = "登录日志DTO")
public class RegisteredClientDto implements Serializable {
    private static final long serialVersionUID = 2800195136076317821L;

    @Schema(title = "主键", example = "1")
    private String id;

    @Schema(title = "登录用户", example = "admin")
    private String username;

    @Schema(title = "远端终端", example = "192.168.0.1")
    private String userAgent;

    @Schema(title = "远端IP", example = "192.168.0.1")
    private String remoteIp;

    @Schema(title = "登录是否成功", example = "true")
    private Boolean successful;

    @Schema(title = "登录时间", example = "2020-12-09 20:15:41")
    private Date loginTime;

    @Schema(title = "登出时间", example = "2020-12-09 20:16:08")
    private Date logoutTime;
}
