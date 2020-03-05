package com.mah.springbootjwteg.controller;

import com.mah.springbootjwteg.model.AuthRequest;
import com.mah.springbootjwteg.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        String generateToken = jwtUtil.generateToken(authRequest.getUsername());
        Map<String, Object> tokenResponse = new HashMap<>();
        tokenResponse.put("token", generateToken);
        tokenResponse.put("tokenValidDuration(in Seconds)", (jwtUtil.extractExpiration(generateToken).getTime() - new Date().getTime()) / 1000);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/checkTokenValidity")
    public Map<String, Object> welcome(HttpServletRequest httpServletRequest) {
        Map<String, Object> stringStringMap = new HashMap<>();
        stringStringMap.put("tokenValidDuration(in Seconds)", (jwtUtil.extractExpiration(httpServletRequest.getHeader("Authorization").substring(7)).getTime() - new Date().getTime()) / 1000);
        return stringStringMap;
    }

}
