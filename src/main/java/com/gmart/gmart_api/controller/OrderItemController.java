package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.Order;
import com.gmart.gmart_api.model.OrderItem;
import com.gmart.gmart_api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/list")
    public ResponseEntity<List<OrderItem>> getAllOrdersItem() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable String orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);

        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItemById(@PathVariable String orderItemId) {
        orderItemService.deleteOrderItemById(orderItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable String orderItemId, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItem);
        if (updatedOrderItem != null) {
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
