package com.jwt.course.springsecurityjwt.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jwt.course.springsecurityjwt.model.User;

@Service
public class TokenService {

    // @Value("${api.security.token.secret}")
    private String secret = "key for api";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("user_product_api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token ", e);
        }
    }

    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("user_product_api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    public Instant genExpirationDate() {
        ZonedDateTime zn = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2);
        return zn.toInstant();
    }

}