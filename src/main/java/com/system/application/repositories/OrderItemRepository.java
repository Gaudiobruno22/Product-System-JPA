package com.system.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.application.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
