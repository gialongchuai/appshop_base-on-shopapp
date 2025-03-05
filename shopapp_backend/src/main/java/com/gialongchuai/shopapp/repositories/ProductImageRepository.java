package com.gialongchuai.shopapp.repositories;

import com.gialongchuai.shopapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<Category, String> {
}
