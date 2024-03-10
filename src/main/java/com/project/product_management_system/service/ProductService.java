package com.project.product_management_system.service;

import java.util.List;

import com.project.product_management_system.dto.ProductDto;



public interface ProductService {
	 public List<ProductDto> getAllProducts();
	 public ProductDto getProductById(Long productId);
	 public ProductDto createProduct(ProductDto productDto);
	 public ProductDto updateProduct(Long productId, ProductDto updatedProduct);
	 public void deleteProduct(Long productId);

}
