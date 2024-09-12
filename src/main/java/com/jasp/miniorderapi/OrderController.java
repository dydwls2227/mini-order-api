package com.jasp.miniorderapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {


    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // GET /order - 모든 주문 조회
    @GetMapping
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // POST /order - 새로운 주문 생성
    @PostMapping
    @ResponseBody
    public Order createOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // PUT /order/{orderId} - 주문 수정
    @PutMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setCategory(updatedOrder.getCategory());
            order.setAmount(updatedOrder.getAmount());
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /order/{orderId} - 주문 삭제
    @DeleteMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
