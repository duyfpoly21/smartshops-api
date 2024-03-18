package vn.smartshop.server.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {
    private String userName;
    private String password;
}
