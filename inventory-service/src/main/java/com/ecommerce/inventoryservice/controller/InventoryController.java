package com.ecommerce.inventoryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {

		this.inventoryService = inventoryService;
	}

	@PostMapping
	public ResponseEntity<InventoryResponse> addInventory(@Validated @RequestBody InventoryRequest request) {

		return new ResponseEntity<>(inventoryService.addInventory(request), HttpStatus.CREATED);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<InventoryResponse> getInventory(@PathVariable Long productId) {

		return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
	}

	@GetMapping("/check/{productId}/{quantity}")
	public ResponseEntity<Boolean> checkStock(@PathVariable Long productId, @PathVariable Integer quantity) {

		return ResponseEntity.ok(inventoryService.checkStock(productId, quantity));
	}

	@PutMapping("/reduce/{productId}/{quantity}")
	public ResponseEntity<String> reduceStock(@PathVariable Long productId, @PathVariable Integer quantity) {

		return ResponseEntity.ok(inventoryService.reduceStock(productId, quantity));
	}
}