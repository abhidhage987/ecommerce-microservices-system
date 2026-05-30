package com.ecommerce.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.productservice.dto.ProductRequest;
import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {

		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@Validated @RequestBody ProductRequest request) {

		return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {

		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
			@Validated @RequestBody ProductRequest request) {

		return ResponseEntity.ok(productService.updateProduct(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

		return ResponseEntity.ok(productService.deleteProduct(id));
	}
}