package vn.smartshop.server.domain.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.smartshop.server.domain.port.UserService;
import vn.smartshop.server.infra.repo.UserRepository;
import vn.smartshop.server.infra.util.AuthUtil;
import vn.smartshop.server.infra.util.EncryptUtil;
import vn.smartshop.server.infra.util.JsonUtil;
import vn.smartshop.server.infra.util.JwtUtil;
import vn.smartshop.server.model.dto.request.RefreshToken;
import vn.smartshop.server.model.dto.request.UserLoginDto;
import vn.smartshop.server.model.dto.response.AuthException;
import vn.smartshop.server.model.dto.response.BusinessException;
import vn.smartshop.server.model.dto.response.TokenResponseDto;
import vn.smartshop.server.model.entity.User;

import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceAdapter implements UserService {
    private final UserRepository userRepository;
    @Override
    public TokenResponseDto getToken(UserLoginDto userLoginDto) throws AuthException {
        User user = userRepository.findById(userLoginDto.getUserName()).orElseThrow(()->new AuthException(401));
        System.out.println(EncryptUtil.encrypt(userLoginDto.getPassword()));
        if (!Objects.equals(EncryptUtil.encrypt(userLoginDto.getPassword()), user.getPassword())) throw new AuthException(401);
        TokenResponseDto token =  TokenResponseDto.builder()
                .accessToken(JwtUtil.generateJwtToken(user, Arrays.asList("role1","role2")))
                .refreshToken(JwtUtil.generateRefreshToken(user.getUserName()))
                .build();
        user.setRefreshToken(token.getRefreshToken());
        userRepository.save(user);
        return token;
    }


    @Override
    public TokenResponseDto getTokenFromRefresh(String refreshToken) throws Exception {
        RefreshToken refreshTokenDto = JsonUtil.readValue(RefreshToken.class,EncryptUtil.decrypt(refreshToken));
        Optional<User> user = userRepository.findById(refreshTokenDto.getUserName());
        if (user.isEmpty() || refreshTokenDto.getEndDate().before(new Date())) throw new AuthException(401);
        return TokenResponseDto.builder()
                .accessToken(JwtUtil.generateJwtToken(user.get(), Arrays.asList("role1","role2")))
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void saveUser(UserLoginDto userLoginDto) {
        if (userRepository.existsById(userLoginDto.getUserName())) throw new BusinessException("User exited");
        User user = User.builder().userName(userLoginDto.getUserName())
                .password(EncryptUtil.encrypt(userLoginDto.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public void initUser() {
        User user = User.builder().userName("admin")
                .password(EncryptUtil.encrypt("1")).permissions(new LinkedList<>())
                .build();
        user.setCreatedBy("admin");
        user.setUpdatedBy("admin");
        userRepository.save(user);
    }

    @Override
    public void logout() {
        User user = userRepository.findById(AuthUtil.getUserName()).orElseThrow(()->new BusinessException("user notfound"));
        user.setRefreshToken(null);
        userRepository.save(user);
    }


}
