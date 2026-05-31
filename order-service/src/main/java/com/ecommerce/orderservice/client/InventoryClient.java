package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @GetMapping("/inventory/check/{productId}/{quantity}")
    Boolean checkStock(
            @PathVariable Long productId,
            @PathVariable Integer quantity);

    @PutMapping("/inventory/reduce/{productId}/{quantity}")
    String reduceStock(
            @PathVariable Long productId,
            @PathVariable Integer quantity);
}