package com.gialongchuai.shopapp.repositories;

import com.gialongchuai.shopapp.entities.Order;
import com.gialongchuai.shopapp.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
