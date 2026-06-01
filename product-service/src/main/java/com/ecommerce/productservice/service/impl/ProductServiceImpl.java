package com.ecommerce.productservice.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ecommerce.productservice.dto.ProductRequest;
import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.productservice.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {

		this.productRepository = productRepository;
	}

	@Override
	public ProductResponse addProduct(ProductRequest request) {

		Product product = new Product();

		product.setProductName(request.getProductName());

		product.setDescription(request.getDescription());

		product.setPrice(request.getPrice());

		product.setStockQuantity(request.getStockQuantity());

		Product savedProduct = productRepository.save(product);

		return new ProductResponse(savedProduct.getId(), savedProduct.getProductName(), savedProduct.getDescription(),
				savedProduct.getPrice(), savedProduct.getStockQuantity());
	}

	@Override
	public List<ProductResponse> getAllProducts() {

		return productRepository
				.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getProductName(),
						product.getDescription(), product.getPrice(), product.getStockQuantity()))
				.collect(Collectors.toList());
	}

	@Cacheable(value = "products", key = "#id")
	@Override
	public ProductResponse getProductById(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

		return new ProductResponse(product.getId(), product.getProductName(), product.getDescription(),
				product.getPrice(), product.getStockQuantity());
	}

	@CacheEvict(value = "products", key = "#id")
	@Override
	public ProductResponse updateProduct(Long id, ProductRequest request) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

		product.setProductName(request.getProductName());

		product.setDescription(request.getDescription());

		product.setPrice(request.getPrice());

		product.setStockQuantity(request.getStockQuantity());

		Product updatedProduct = productRepository.save(product);

		return new ProductResponse(updatedProduct.getId(), updatedProduct.getProductName(),
				updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getStockQuantity());
	}

	@CacheEvict(value = "products", key = "#id")
	@Override
	public String deleteProduct(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

		productRepository.delete(product);

		return "Product Deleted Successfully";
	}

}