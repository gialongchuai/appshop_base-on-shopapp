package com.gialongchuai.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gialongchuai.shopapp.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {}
