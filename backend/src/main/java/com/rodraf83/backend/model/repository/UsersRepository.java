package com.rodraf83.backend.model.repository;

import com.rodraf83.backend.model.entity.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

}
