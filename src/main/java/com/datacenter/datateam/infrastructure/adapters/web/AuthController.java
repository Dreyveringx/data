package com.datacenter.datateam.infrastructure.adapters.web;

import com.datacenter.datateam.application.services.LoginService;
import com.datacenter.datateam.domain.ports.DocumentTypeRepositoryPort;
import com.datacenter.datateam.infrastructure.adapters.web.dto.DocumentTypeResponse;
import com.datacenter.datateam.infrastructure.adapters.web.dto.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.web.dto.LoginResponse;
import com.datacenter.datateam.infrastructure.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datateam")
public class AuthController {
    private final LoginService loginService;
    private final DocumentTypeRepositoryPort documentTypeRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(LoginService loginService, 
                          DocumentTypeRepositoryPort documentTypeRepository, 
                          UserDetailsService userDetailsService,
                          JwtTokenProvider jwtTokenProvider) {
        this.loginService = loginService;
        this.documentTypeRepository = documentTypeRepository;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/document-types")
    public ResponseEntity<List<DocumentTypeResponse>> getDocumentTypes() {
        return ResponseEntity.ok(
            documentTypeRepository.findAll().stream()
                .map(dt -> new DocumentTypeResponse(dt.getId(), dt.getName()))
                .toList()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        boolean isAuthenticated = loginService.authenticate(
            request.getDocumentTypeId(),
            request.getDocumentNumber(),
            request.getPassword()
        );

        if (isAuthenticated) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getDocumentNumber());
            String token = jwtTokenProvider.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(true, "Login exitoso", token));
        }

        return ResponseEntity.status(401).body(new LoginResponse(false, "Credenciales inválidas", ""));
    }
}
