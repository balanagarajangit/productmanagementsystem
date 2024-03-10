package com.project.product_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.product_management_system.dto.ProductDto;
import com.project.product_management_system.entity.Product;
import com.project.product_management_system.exception.ResourceNotFoundException;
import com.project.product_management_system.mapper.ProductMapper;
import com.project.product_management_system.repository.ProductRepository;

@Service
public class ProductServiceImple implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> getAllProducts() 
	{
		List<Product> products = productRepository.findAll();
		return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Long productId) 
	{
		Product product = productRepository.findById(productId)
		.orElseThrow(()->
		new ResourceNotFoundException("Product is not exist with given id : "+productId));
		return ProductMapper.mapToProductDto(product);
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = ProductMapper.mapToProduct(productDto);
		Product savedProduct = productRepository.save(product);
		return ProductMapper.mapToProductDto(savedProduct);
	}

	@Override
	public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("Product is not exists with given id: "+productId)
				);
		
		product.setName(updatedProduct.getName());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		product.setQuantity(updatedProduct.getQuantity());
		
		Product updatedProductObj = productRepository.save(product);
		return ProductMapper.mapToProductDto(updatedProductObj);
	}

	@Override
	public void deleteProduct(Long productId) {
	    productRepository.deleteById(productId);
	}

}
