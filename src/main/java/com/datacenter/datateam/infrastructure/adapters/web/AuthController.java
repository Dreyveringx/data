package com.datacenter.datateam.infrastructure.adapters.web;

import com.datacenter.datateam.application.services.LoginService;
import com.datacenter.datateam.domain.ports.DocumentTypeRepositoryPort;
import com.datacenter.datateam.infrastructure.adapters.web.dto.DocumentTypeResponse;
import com.datacenter.datateam.infrastructure.adapters.web.dto.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.web.dto.LoginResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datateam.")
public class AuthController {
    private final LoginService loginService;
    private final DocumentTypeRepositoryPort documentTypeRepository;

    public AuthController(LoginService loginService, DocumentTypeRepositoryPort documentTypeRepository) {
        this.loginService = loginService;
        this.documentTypeRepository = documentTypeRepository;
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
            request.documentTypeId(),
            request.documentNumber(),
            request.password()
        );

        if (isAuthenticated) {
            return ResponseEntity.ok(new LoginResponse(true, "Login exitoso"));
        }
        return ResponseEntity.status(401).body(new LoginResponse(false, "Credenciales inválidas"));
    }
}