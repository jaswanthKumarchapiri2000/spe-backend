package com.example.admin;

import com.example.config.JwtService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository repository ;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public Object authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );

        var user = repository.findByName(request.getName())
                .orElseThrow();;
        var jwtToken =jwtService.generateToken(user);
        return AuthenticateResponce.builder()
                .token(jwtToken)
                .build();
    }
}
