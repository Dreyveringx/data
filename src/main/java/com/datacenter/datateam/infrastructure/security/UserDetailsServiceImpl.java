package com.datacenter.datateam.infrastructure.security;

import com.datacenter.datateam.domain.model.User;
import com.datacenter.datateam.infrastructure.adapters.persistence.JpaUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaUserRepository userRepository;

    public UserDetailsServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String documentNumber) throws UsernameNotFoundException {
        User user = userRepository.findByDocumentTypeAndDocumentNumber(1L, documentNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getDocumentNumber())
                .password(user.getPassword())
                .roles("USER") 
                .build();
    }
}
