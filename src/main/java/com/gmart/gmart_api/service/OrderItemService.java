package com.gmart.gmart_api.service;

import com.gmart.gmart_api.exceptions.OrderItemNotFoundException;
import com.gmart.gmart_api.model.OrderItem;
import com.gmart.gmart_api.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        try {
            return orderItemRepository.save(orderItem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save an order item", e);
        }
    }

    public List<OrderItem> getAllOrderItems() {
        try {
            return orderItemRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch order items", e);
        }
    }

    public OrderItem getOrderItemById(String orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderItemNotFoundException("Order item not found with Id " + orderItemId));
    }

    public void deleteOrderItemById(String orderItemId) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(orderItemId);

        if (existingOrderItem.isPresent()) {
            try {
                orderItemRepository.deleteById(orderItemId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete the order item with ID: " + orderItemId);
            }
        } else {
            throw new OrderItemNotFoundException("Order item not found with Id " + orderItemId);
        }
    }

    public OrderItem updateOrderItem(String orderItemId, OrderItem orderItemDetails) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new OrderItemNotFoundException("Order item not found with id " + orderItemId));

        try {
            orderItem.setProduct(orderItemDetails.getProduct());
            orderItem.setQuantity(orderItemDetails.getQuantity());
            orderItem.setTotalPrice(orderItemDetails.getTotalPrice());

            return orderItemRepository.save(orderItem);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the order item for Id " + orderItemId);
        }
    }
}
