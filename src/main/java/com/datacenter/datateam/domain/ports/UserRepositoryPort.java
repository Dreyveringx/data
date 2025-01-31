package com.datacenter.datateam.domain.ports;

import java.util.Optional;

import com.datacenter.datateam.domain.model.User;

public interface UserRepositoryPort {

    Optional<User> findByDocumentTypeAndDocumentNumber(Long documentTypeId, String documentNumber);

}
