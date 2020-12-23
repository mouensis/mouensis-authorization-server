package com.mouensis.server.authorization.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhuyuan
 * @date 2020/12/19 12:04
 */
@Entity
@Table(name = "oauth2_registered_client")
@Setter
@Getter
@ToString
@DynamicInsert
@DynamicUpdate
public class RegisteredClientEntity implements Serializable {
    private static final long serialVersionUID = -2699187464097826357L;
    /**
     * 用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成).
     */
    @Id
    @Column(length = 36)
    private String clientId;
    /**
     * 用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成).
     */
    @Column(length = 36)
    private String clientSecret;
    /**
     * 指定客户端支持的grant_type,
     * 可选值包括authorization_code,password,refresh_token,implicit,client_credentials,
     * 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".
     * 在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,
     * 最常用的grant_type组合有:
     * "authorization_code,refresh_token"(针对通过浏览器访问的客户端);
     * "password,refresh_token"(针对移动设备的客户端).
     * implicit与client_credentials
     */
    @Column(length = 100)
    private String grantTypes;
    /**
     * 指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write".
     */
    @Column(length = 50)
    private String scope;
    /**
     * 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".
     */
    @Column(length = 50)
    private String authorities;
    /**
     * 客户端的重定向URI,可为空
     */
    @Column()
    private String redirectUri;
    /**
     * 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
     */
    private Long refreshTokenValidity;
    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
     */
    private Long accessTokenValidity;
    /**
     * 设置用户是否自动Approval操作.
     */
    @Column(name = "is_auto_approve")
    private Boolean autoApprove;
    /**
     * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String additionalInformation;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date modifiedAt;
}
