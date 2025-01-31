package com.datacenter.datateam.infrastructure.adapters.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.model.DocumentType;
import com.datacenter.datateam.domain.ports.DocumentTypeRepositoryPort;

@Repository
public interface JpaDocumentTypeRepository extends JpaRepository<DocumentType, Long>, DocumentTypeRepositoryPort {

    @Override
    default List<DocumentType> findAll() {
        return findAllDocuments();
    }

    List<DocumentType> findAllDocuments();
}
