package vn.smartshop.server.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.smartshop.server.domain.port.UserService;
import vn.smartshop.server.infra.util.JsonUtil;
import vn.smartshop.server.infra.util.ResponseUtil;
import vn.smartshop.server.model.dto.request.UserLoginDto;
import vn.smartshop.server.model.dto.response.AuthException;
import vn.smartshop.server.model.dto.response.Response;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userServicePort;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('role1')")
    public ResponseEntity<Response> saveUser(@RequestBody UserLoginDto userLoginDto) {
        System.out.println(JsonUtil.writeValueAsString(SecurityContextHolder.getContext().getAuthentication()));
//        userServicePort.saveUser(userLoginDto);
        return ResponseUtil.success(null);
    }

    @PostMapping("/get-token")
    public ResponseEntity<Response> getToken(@RequestBody UserLoginDto userLoginDto) throws AuthException {
        userServicePort.getToken(userLoginDto);
        return ResponseUtil.success(userServicePort.getToken(userLoginDto));
    }

    @GetMapping("/get-token-from-refresh")
    public ResponseEntity<Response> getTokenFromRefresh(@RequestParam String refresh) throws Exception {
        userServicePort.getTokenFromRefresh(refresh);
        return ResponseUtil.success(userServicePort.getTokenFromRefresh(refresh));
    }

    @PostMapping("/init-user")
    public ResponseEntity<Response> initUser() {
        userServicePort.initUser();
        return ResponseUtil.success(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout() {
        userServicePort.logout();
        return ResponseUtil.success(null);
    }
}
