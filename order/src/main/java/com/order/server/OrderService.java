package com.order.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.order.Entity.Order;


@Service
public interface OrderService extends JpaRepository<Order, Long> {

}
