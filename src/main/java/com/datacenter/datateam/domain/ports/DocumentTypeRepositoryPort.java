package com.datacenter.datateam.domain.ports;

import java.util.List;

import com.datacenter.datateam.domain.model.DocumentType;

public interface DocumentTypeRepositoryPort {

    List<DocumentType> findAll();

}
