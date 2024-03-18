package vn.smartshop.server.domain.port;

import vn.smartshop.server.model.dto.request.UserLoginDto;
import vn.smartshop.server.model.dto.response.AuthException;
import vn.smartshop.server.model.dto.response.TokenResponseDto;

public interface UserService {
    TokenResponseDto getToken(UserLoginDto userLoginDto) throws AuthException;

    TokenResponseDto getTokenFromRefresh(String refreshToken) throws Exception;

    void saveUser(UserLoginDto userLoginDto);

    void initUser();

    void logout();
}
