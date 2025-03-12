package com.gialongchuai.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gialongchuai.shopapp.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {}
