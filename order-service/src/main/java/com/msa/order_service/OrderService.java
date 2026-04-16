package com.msa.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public Map<String, Object> createOrder(Long userId, Long productId, int quantity) {
        // Feign으로 다른 서비스 데이터 조회
        UserResponse user = userClient.getUserById(userId);
        ProductResponse product = productClient.getProductById(productId);

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        orderRepository.save(order);

        // 결과 조합해서 반환
        Map<String, Object> result = new HashMap<>();
        result.put("orderId", order.getId());
        result.put("username", user.getUsername());
        result.put("productName", product.getName());
        result.put("quantity", quantity);
        result.put("totalPrice", order.getTotalPrice());
        return result;
    }
}