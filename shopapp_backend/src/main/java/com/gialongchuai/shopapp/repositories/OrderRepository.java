package com.gialongchuai.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gialongchuai.shopapp.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {}
