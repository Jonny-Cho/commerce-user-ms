package com.commerce.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);

    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String username);
}
