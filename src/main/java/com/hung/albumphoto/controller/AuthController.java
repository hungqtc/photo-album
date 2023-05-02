package com.hung.albumphoto.controller;

import com.hung.albumphoto.dto.LoginRequest;
import com.hung.albumphoto.dto.LoginResponse;
import com.hung.albumphoto.dto.UserDTO;
import com.hung.albumphoto.dto.UserDetails;
import com.hung.albumphoto.service.UserService;
import com.hung.albumphoto.ultils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
/*@CrossOrigin(origins = "http://localhost:4200")*/
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtToken;

    @Autowired(required=false)
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new LoginResponse(jwtToken.generateToken(loginRequest.getEmail())));
        } else  {
            throw new UsernameNotFoundException("Invalid user credentials");
        }
    }
}
