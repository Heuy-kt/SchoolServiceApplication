package com.heuy_kt.AdminApp.auth.serviceImpl;

import com.heuy_kt.AdminApp.auth.AuthenticationRequest;
import com.heuy_kt.AdminApp.auth.AuthenticationResponse;
import com.heuy_kt.AdminApp.auth.RegisterRequest;
import com.heuy_kt.AdminApp.config.JwtService;
import com.heuy_kt.AdminApp.entity.Admin;
import com.heuy_kt.AdminApp.enums.Roles;
import com.heuy_kt.AdminApp.repo.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Admin.builder()
                .fullName(request.getFirstName()+request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Roles.ADMIN)
                .build();
        adminRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = adminRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
