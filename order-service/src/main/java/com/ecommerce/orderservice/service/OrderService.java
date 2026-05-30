package com.ecommerce.orderservice.service;

import java.util.List;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();
}