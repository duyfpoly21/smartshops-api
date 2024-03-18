package vn.smartshop.server.model.dto.response;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
