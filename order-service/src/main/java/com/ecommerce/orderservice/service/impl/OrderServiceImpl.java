package com.ecommerce.orderservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.client.ProductClient;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.dto.ProductResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductClient productClient;

	public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient) {

		this.orderRepository = orderRepository;
		this.productClient = productClient;
	}

	@Override
	public OrderResponse createOrder(OrderRequest request) {

		Order order = new Order();

		order.setUserId(request.getUserId());
		order.setProductId(request.getProductId());
		order.setQuantity(request.getQuantity());

		ProductResponse product = productClient.getProductById(request.getProductId());

		BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

		order.setTotalPrice(totalPrice.doubleValue());

		Order savedOrder = orderRepository.save(order);

		return new OrderResponse(savedOrder.getId(), savedOrder.getUserId(), savedOrder.getProductId(),
				savedOrder.getQuantity(), savedOrder.getTotalPrice());
	}

	@Override
	public List<OrderResponse> getAllOrders() {

		return orderRepository.findAll().stream().map(order -> new OrderResponse(order.getId(), order.getUserId(),
				order.getProductId(), order.getQuantity(), order.getTotalPrice())).collect(Collectors.toList());
	}
}