package com.ecommerce.inventoryservice.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import com.ecommerce.inventoryservice.exception.InventoryNotFoundException;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import com.ecommerce.inventoryservice.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	public InventoryServiceImpl(InventoryRepository inventoryRepository) {

		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public InventoryResponse addInventory(InventoryRequest request) {

		Inventory inventory = new Inventory();

		inventory.setProductId(request.getProductId());

		inventory.setAvailableQuantity(request.getAvailableQuantity());

		Inventory savedInventory = inventoryRepository.save(inventory);

		return new InventoryResponse(savedInventory.getId(), savedInventory.getProductId(),
				savedInventory.getAvailableQuantity());
	}

	@Override
	public InventoryResponse getInventoryByProductId(Long productId) {

		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory Not Found"));

		return new InventoryResponse(inventory.getId(), inventory.getProductId(), inventory.getAvailableQuantity());
	}
	
	@Override
	public boolean checkStock(
	        Long productId,
	        Integer quantity) {

	    Inventory inventory =
	            inventoryRepository
	                    .findByProductId(productId)
	                    .orElseThrow(() ->
	                            new InventoryNotFoundException(
	                                    "Inventory Not Found"));

	    return inventory.getAvailableQuantity()
	            >= quantity;
	}
	
	@Override
	public String reduceStock(
	        Long productId,
	        Integer quantity) {

	    Inventory inventory =
	            inventoryRepository
	                    .findByProductId(productId)
	                    .orElseThrow(() ->
	                            new InventoryNotFoundException(
	                                    "Inventory Not Found"));

	    if (inventory.getAvailableQuantity()
	            < quantity) {

	        throw new RuntimeException(
	                "Insufficient Stock");
	    }

	    inventory.setAvailableQuantity(
	            inventory.getAvailableQuantity()
	                    - quantity);

	    inventoryRepository.save(inventory);

	    return "Stock Updated Successfully";
	}
}