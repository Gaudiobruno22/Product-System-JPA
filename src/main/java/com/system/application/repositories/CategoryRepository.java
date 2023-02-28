package com.system.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.application.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
