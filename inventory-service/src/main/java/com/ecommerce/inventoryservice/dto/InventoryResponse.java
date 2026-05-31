package com.ecommerce.inventoryservice.dto;

public class InventoryResponse {

    private Long id;
    private Long productId;
    private Integer availableQuantity;

    public InventoryResponse() {
    }

    public InventoryResponse(
            Long id,
            Long productId,
            Integer availableQuantity) {

        this.id = id;
        this.productId = productId;
        this.availableQuantity = availableQuantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

    
}