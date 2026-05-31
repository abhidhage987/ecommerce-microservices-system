package com.ecommerce.orderservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.client.InventoryClient;
import com.ecommerce.orderservice.client.ProductClient;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.dto.ProductResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.InsufficientStockException;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductClient productClient;
	private final InventoryClient inventoryClient;

	public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient,
			InventoryClient inventoryClient) {

		this.orderRepository = orderRepository;
		this.productClient = productClient;
		this.inventoryClient = inventoryClient;
	}

	@Override
	public OrderResponse createOrder(OrderRequest request) {

		Order order = new Order();

		order.setUserId(request.getUserId());
		order.setProductId(request.getProductId());
		order.setQuantity(request.getQuantity());

		ProductResponse product = productClient.getProductById(request.getProductId());

		Boolean stockAvailable = inventoryClient.checkStock(request.getProductId(), request.getQuantity());

		if (!stockAvailable) {

			throw new InsufficientStockException("Insufficient Stock Available");
		}

		BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

		order.setTotalPrice(totalPrice.doubleValue());
         
		inventoryClient.reduceStock(
		        request.getProductId(),
		        request.getQuantity());
		
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