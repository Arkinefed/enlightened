package com.arkinefed.luminous.repository;

import com.arkinefed.luminous.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.buyer.id = :id")
    List<Order> findOrdersByUserId(long id);
}
