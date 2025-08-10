package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                         UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    record AuthRequest(String username, String password) {}
    record RegisterRequest(String username, String password, String fullName) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
            var userOpt = userRepository.findByUsername(req.username());
            var user = userOpt.orElseThrow();
            
            // Convert Set<String> to List<String>
            List<String> roles = user.getRoles().stream().collect(Collectors.toList());
            var token = jwtUtil.generateToken(user.getUsername(), roles);
            
            return ResponseEntity.ok(Map.of(
                "token", token,
                "username", user.getUsername()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest r) {
        if (userRepository.existsByUsername(r.username())) {
            return ResponseEntity.badRequest().body(Map.of("error", "username taken"));
        }
        User u = new User();
        u.setUsername(r.username());
        u.setFullName(r.fullName());
        u.setPassword(passwordEncoder.encode(r.password()));
        u.setRoles(Set.of("ROLE_MEMBER"));
        userRepository.save(u);
        return ResponseEntity.ok(Map.of("message", "registered"));
    }
}