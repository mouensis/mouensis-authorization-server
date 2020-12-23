package com.mouensis.server.authorization.entity;

import com.mouensis.server.authorization.entity.pk.AuthorizedTokenEntityPk;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 访问Token
 *
 * @author zhuyuan
 * @date 2020/12/22 19:50
 */
@Entity
@Table(name = "oauth2_authorized_token")
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@IdClass(AuthorizedTokenEntityPk.class)
public class AuthorizedTokenEntity implements Serializable {
    private static final long serialVersionUID = -5524299371360495087L;
    /**
     * 客户端ID
     */
    @Id
    @Column(length = 36)
    private String authorizedClientId;
    /**
     * 用户名，可空
     */
    @Id
    @Column(length = 36)
    private String principalName;
    /**
     * 访问Token值
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String accessTokenValue;
    /**
     * 刷新Token值
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String refreshTokenValue;
    /**
     * 认证信息
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] authorizationObject;
    /**
     * 创建时间
     */
    @CreationTimestamp
    private Date createdAt;
    /**
     * 更新时间
     */
    @UpdateTimestamp
    private Date modifiedAt;
}
