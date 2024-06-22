package com.example.cloud.eureka.idea_y.controllers;

import com.example.cloud.eureka.idea_y.dto.ReqRes;
import com.example.cloud.eureka.idea_y.services.AuthService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUp){
        return ResponseEntity.ok(authService.signUp(signUp));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes refreshRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshRequest));
    }
}
