package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.dto.CreateLogin;
import ages.pucrs.hackathon.dto.LoginRequest;
import ages.pucrs.hackathon.dto.LoginResponse;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody CreateLogin user) {
        UserEntity newUser = authService.register(user);
        return ResponseEntity.ok(newUser);
    }
}