package vn.smartshop.server.infra.util;

import org.springframework.security.core.context.SecurityContextHolder;
import vn.smartshop.server.model.dto.request.TokenDto;

public class AuthUtil {
    public static String getUserName(){
        try {
            return getAuth().getUserName();
        }catch (Exception e){
            return null;
        }
    }

    public static TokenDto getAuth(){
        return (TokenDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
