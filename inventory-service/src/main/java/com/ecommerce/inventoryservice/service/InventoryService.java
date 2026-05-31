package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;

public interface InventoryService {

	InventoryResponse addInventory(InventoryRequest request);

	InventoryResponse getInventoryByProductId(Long productId);

	boolean checkStock(Long productId, Integer quantity);

	String reduceStock(Long productId, Integer quantity);
}