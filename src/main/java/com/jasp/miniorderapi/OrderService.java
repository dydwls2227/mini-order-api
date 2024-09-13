package com.jasp.miniorderapi;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void save(Order order) {
        Long id = order.getId();
        String buyer = order.getBuyer();
        Category category = order.getCategory();
        LocalDateTime dateTime = order.getOrderDate();
        Double amount = order.getAmount();

        Order order1 = new Order(id,buyer,category,dateTime,amount);
        orderRepository.save(order1);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public void update(Long id, Order order) {
        Order order1 = orderRepository.findById(id).get();
        order1.setBuyer(order.getBuyer());
        order1.setCategory(order.getCategory());
        order1.setOrderDate(order.getOrderDate());
        order1.setAmount(order.getAmount());
    }

    public void remove(Long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("해당 ID가 존재하지 않습니다: " + id);
        }
    }
}
