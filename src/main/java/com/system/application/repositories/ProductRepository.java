package com.system.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.application.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
