package vn.smartshop.server.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String userName;
    private List<String> permissionLst;
    private String role;
    private Long iat;
    private Long exp;
    private String sub;
}
