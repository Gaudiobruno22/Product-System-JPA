package com.system.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.application.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
