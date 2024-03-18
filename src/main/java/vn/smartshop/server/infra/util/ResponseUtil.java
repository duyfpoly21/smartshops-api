package vn.smartshop.server.infra.util;


import org.springframework.http.ResponseEntity;
import vn.smartshop.server.model.dto.response.Response;

public class ResponseUtil {

    public static ResponseEntity<Response> success(Object data){
        Response response = Response.builder().status(200).data(data).message("Success").build();
        return ResponseEntity.ok(response);
    };
}
