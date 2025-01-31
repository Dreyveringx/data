package com.datacenter.datateam.infrastructure.adapters.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.model.User;
import com.datacenter.datateam.domain.ports.UserRepositoryPort;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepositoryPort {

    @Query("SELECT u FROM User u WHERE u.documentTypeId = :documentTypeId AND u.documentNumber = :documentNumber")
    Optional<User> findByDocumentTypeAndDocumentNumber(@Param("documentTypeId") Long documentTypeId, 
                                                       @Param("documentNumber") String documentNumber);
}

