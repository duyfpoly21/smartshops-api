package vn.smartshop.server.model.dto.response;

import lombok.Getter;

@Getter
public class AuthException extends Exception{
    private Integer error;
    public AuthException(Integer error){
        this.error = error;
    }
}
