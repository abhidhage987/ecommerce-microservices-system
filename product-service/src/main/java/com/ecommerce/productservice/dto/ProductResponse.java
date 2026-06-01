package com.ecommerce.productservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductResponse implements Serializable {

    private Long id;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;

    public ProductResponse() {
    }

    public ProductResponse(Long id,
                           String productName,
                           String description,
                           BigDecimal price,
                           Integer stockQuantity) {

        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}