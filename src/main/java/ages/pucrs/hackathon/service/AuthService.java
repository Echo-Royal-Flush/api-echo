package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.dto.CreateLogin;
import ages.pucrs.hackathon.dto.LoginRequest;
import ages.pucrs.hackathon.dto.LoginResponse;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.repository.UserRepository;
import ages.pucrs.hackathon.sec.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserEntity user = (UserEntity) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);

            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setRole(user.getRole().name());

            return response;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }

    public UserEntity register(CreateLogin user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(UserEntity.Role.EMPLOYEE);
        userEntity.setPosition(user.getPosition());
        return userRepository.save(userEntity);
    }
}
