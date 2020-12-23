package com.mouensis.server.authorization.dto.client;

import com.mouensis.framework.web.domain.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 查询登录日志DTO
 *
 * @author zhuyuan
 * @date 2020/12/9 20:18
 */
@Getter
@Setter
@ToString
@Schema(title = "查询登录日志DTO")
public class QueryRegisteredClientDto implements QueryDto, Serializable {
    private static final long serialVersionUID = 5027438877596442577L;

    @Schema(title = "登录用户", example = "admin")
    private String username;

    @Schema(title = "远端IP", example = "192.168.0.1")
    private String remoteIp;

    @Schema(title = "登录是否成功", example = "true")
    private Boolean successful;
}
