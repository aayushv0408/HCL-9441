package com.hcld2.jwtdemo.service;

import com.hcld2.jwtdemo.dto.AuthRequest;
import com.hcld2.jwtdemo.dto.AuthResponse;
import com.hcld2.jwtdemo.dto.StatusResponse;
import com.hcld2.jwtdemo.entity.User;
import com.hcld2.jwtdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    @Transactional
    public StatusResponse registerUser(AuthRequest authRequest) {
        if(users.findByUsername(authRequest.username()).isPresent()){
            return new StatusResponse("error","Username already in use");
        }
        users.save(new User(authRequest.username(), encoder.encode(authRequest.password())));
        return new StatusResponse("message","User successfully registered");
    }

    public AuthResponse login(AuthRequest req) {
        var user = users.findByUsername(req.username()).orElse(null);
                if(user == null || !encoder.matches(req.password(), user.getPassword())){
                    return new AuthResponse("error","username or password is incorrect");
                }
        return new AuthResponse(jwt.generateToken(user.getUsername()), user.getUsername());
    }

}
