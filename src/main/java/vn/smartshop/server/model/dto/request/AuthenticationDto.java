package vn.smartshop.server.model.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Setter
@Getter
public class AuthenticationDto extends UsernamePasswordAuthenticationToken {
    private String role;

    public AuthenticationDto(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthenticationDto(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
