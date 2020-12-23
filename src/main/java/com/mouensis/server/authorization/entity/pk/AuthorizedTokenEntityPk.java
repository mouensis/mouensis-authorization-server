package com.mouensis.server.authorization.entity.pk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * 授权Token主键
 *
 * @author zhuyuan
 * @date 2020/12/22 10:26
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedTokenEntityPk implements Serializable {
    private static final long serialVersionUID = -3725660189893340312L;
    private String authorizedClientId;
    private String principalName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizedTokenEntityPk that = (AuthorizedTokenEntityPk) o;
        return Objects.equals(authorizedClientId, that.authorizedClientId) &&
                Objects.equals(principalName, that.principalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizedClientId, principalName);
    }
}
