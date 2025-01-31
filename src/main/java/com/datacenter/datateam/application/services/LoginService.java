package com.datacenter.datateam.application.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.model.User;
import com.datacenter.datateam.domain.ports.UserRepositoryPort;

@Service
public class LoginService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(Long documentTypeId, String documentNumber, String password) {
        Optional<User> user = userRepository.findByDocumentTypeAndDocumentNumber(documentTypeId, documentNumber);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

}
