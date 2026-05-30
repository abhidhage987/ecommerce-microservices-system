package com.ecommerce.orderservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;

@Service
public class OrderServiceImpl
        implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse createOrder(
            OrderRequest request) {

        Order order = new Order();

        order.setUserId(
                request.getUserId());

        order.setProductId(
                request.getProductId());

        order.setQuantity(
                request.getQuantity());


        double totalPrice =
                request.getQuantity() * 1000;

        order.setTotalPrice(totalPrice);

        Order savedOrder =
                orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice());
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order ->
                        new OrderResponse(
                                order.getId(),
                                order.getUserId(),
                                order.getProductId(),
                                order.getQuantity(),
                                order.getTotalPrice()))
                .collect(Collectors.toList());
    }
}