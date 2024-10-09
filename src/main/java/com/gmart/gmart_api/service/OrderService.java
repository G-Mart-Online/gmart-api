package com.gmart.gmart_api.service;


import com.gmart.gmart_api.model.Order;
import com.gmart.gmart_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save an order", e);
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch orders", e);
        }
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found with Id " + orderId));
    }

    public void deleteOrderById(String orderId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);

        if (existingOrder.isPresent()) {
            try {
                orderRepository.deleteById(orderId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete the order with ID: " + orderId);
            }
        } else {
            throw new IllegalArgumentException("Order not found with Id " + orderId);
        }
    }

    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        try {
            order.setStatus(status);
            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the order status for Order ID: " + orderId);
        }
    }

}
