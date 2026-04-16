package com.msa.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, String> body) {
        Map<String, Object> result = orderService.createOrder(
                Long.parseLong(body.get("userId")),
                Long.parseLong(body.get("productId")),
                Integer.parseInt(body.get("quantity"))
        );
        return ResponseEntity.ok(result);
    }
}