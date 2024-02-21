package com.example.BankApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    public String generateJwt(Authentication auth) {
        try {
            if (jwtEncoder == null || jwtDecoder == null) {
                throw new IllegalStateException("JwtEncoder or JwtDecoder is not properly configured.");
            }

            Instant now = Instant.now();

            String scope = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .subject(auth.getName())
                    .claim("roles", scope)
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating JWT";
        }
    }

}
