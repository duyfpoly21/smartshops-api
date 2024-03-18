package vn.smartshop.server.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vn.smartshop.server.infra.util.JwtUtil;
import vn.smartshop.server.model.dto.request.TokenDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Filter extends BasicAuthenticationFilter {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public Filter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public Filter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String token = request.getHeader(HEADER_STRING);
        if (JwtUtil.validateJwtToken(token)) return null;
        token = token.replace(TOKEN_PREFIX, "");
        String[] splitString = token.split("\\.");
        String base64EncodedBody = splitString[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        try {
            TokenDto tokenBody = objectMapper.readValue(body, TokenDto.class);
            Date date = new Date(tokenBody.getExp() * 1000);
            if (date.before(new Date())) {
                return null;
            }
             List<SimpleGrantedAuthority> authorities = tokenBody.getPermissionLst().stream().map(
                            SimpleGrantedAuthority::new)
                    .collect(
                            Collectors.toList());
            return new UsernamePasswordAuthenticationToken(tokenBody, request.getHeader(HEADER_STRING), authorities);
        } catch (Exception e) {
            return null;
        }

    }


}