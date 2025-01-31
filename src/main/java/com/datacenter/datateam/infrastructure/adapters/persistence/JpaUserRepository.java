package com.datacenter.datateam.infrastructure.adapters.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.model.User;
import com.datacenter.datateam.domain.ports.UserRepositoryPort;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepositoryPort{

    @Override
    default Optional<User> findByDocumentTypeAndDocumentNumber(Long documentTypeId, String documentNumber) {
        return findByDocumentTypeAndDocumentNumber(documentTypeId, documentNumber);
    };
}
