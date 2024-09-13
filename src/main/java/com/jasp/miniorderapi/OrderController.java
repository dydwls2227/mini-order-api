package com.jasp.miniorderapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // GET /order - 모든 주문 조회
    @GetMapping("/order")
    @ResponseBody
    public ResponseEntity<?> read() {
        List<Order> result = orderService.getAllOrder();
        return ResponseEntity.ok().body(result);
    }

    // POST /order - 새로운 주문 생성
    @ResponseBody
    @PostMapping("/order/save")
    public ResponseEntity<?> save(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.ok().build();
    }

    // PUT /order/{orderId} - 주문 수정
    @PutMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long orderId, @RequestBody Order order) {
        orderService.update(orderId, order);
        return ResponseEntity.ok().build();
    }

    // DELETE /order/{orderId} - 주문 삭제
    @DeleteMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.remove(orderId);
        return ResponseEntity.ok().build();
    }
}
