package com.system.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.application.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
