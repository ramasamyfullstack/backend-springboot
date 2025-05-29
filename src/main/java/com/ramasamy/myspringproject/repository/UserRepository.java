package com.ramasamy.myspringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramasamy.myspringproject.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
