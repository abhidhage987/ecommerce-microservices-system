package com.ecommerce.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {

		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Validated @RequestBody OrderRequest request) {

		return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders() {

		return ResponseEntity.ok(orderService.getAllOrders());
	}
}