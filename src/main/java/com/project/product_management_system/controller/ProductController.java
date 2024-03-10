package com.project.product_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.product_management_system.dto.ProductDto;
import com.project.product_management_system.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Product Management")
public class ProductController 
{
	@Autowired
	private ProductService productService;
	

	//Bulid create product rest api
	@ApiOperation(value = "Create a new product")
	@PostMapping
	 public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
	
	//Bulid get product rest api
	@ApiOperation(value = "Get a product by ID")
	@GetMapping("{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId)
	{
		ProductDto productDto = productService.getProductById(productId);
		return ResponseEntity.ok(productDto);
	}
	
	//Build Get All Products Rest Api
	@ApiOperation(value = "Get all products")
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
	//build update product rest api
	@ApiOperation(value = "update a product")
	@PutMapping("{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDto updatedProduct) {
        ProductDto productDto = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(productDto);
    }
		
	//build delete product rest Api
	@ApiOperation(value = "delete a product by id")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId)
	{
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Employee deleted successfully.");
	}
	

}
