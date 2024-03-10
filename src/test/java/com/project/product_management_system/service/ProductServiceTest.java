package com.project.product_management_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.project.product_management_system.dto.ProductDto;
import com.project.product_management_system.entity.Product;
import com.project.product_management_system.exception.ResourceNotFoundException;
import com.project.product_management_system.mapper.ProductMapper;
import com.project.product_management_system.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Spy
    @InjectMocks
    private ProductService productService = new ProductServiceImple(); // Assuming ProductServiceImpl implements ProductService

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", "Description 1", 10.0, 5, LocalDate.now(), LocalDate.now().plusDays(30));
        Product product2 = new Product(2L, "Product 2", "Description 2", 20.0, 10, LocalDate.now(), LocalDate.now().plusDays(60));
        List<Product> products = Arrays.asList(product1, product2);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(products, pageRequest, products.size());

        when(productRepository.findAll(pageRequest)).thenReturn(productPage);

        List<ProductDto> productDtos = productService.getAllProducts();

        assertEquals(2, productDtos.size());
        assertEquals("Product 1", productDtos.get(0).getName());
        assertEquals("Product 2", productDtos.get(1).getName());
    }

    @Test
    public void testGetProductById() {
        ProductDto productDto = new ProductDto(1L, "Product 1", "Description 1", 10.0, 5, LocalDate.now(), LocalDate.now().plusDays(30));
        Product product = ProductMapper.mapToProduct(productDto);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDto result = productService.getProductById(1L);

        assertEquals("Product 1", result.getName());
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
    }

    @Test
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto(null, "Product 1", "Description 1", 10.0, 5, LocalDate.now(), LocalDate.now().plusDays(30));
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = new Product(1L, "Product 1", "Description 1", 10.0, 5, LocalDate.now(), LocalDate.now().plusDays(30));

        when(productRepository.save(product)).thenReturn(savedProduct);

        ProductDto result = productService.createProduct(productDto);

        assertEquals("Product 1", result.getName());
        assertEquals("Description 1", result.getDescription());
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;

        ProductDto updatedProductDto = new ProductDto(productId, "Updated Product", "Updated Description", 20.0, 10, LocalDate.now(), LocalDate.now().plusDays(60));
        Product updatedProduct = ProductMapper.mapToProduct(updatedProductDto);

        when(productRepository.existsById(productId)).thenReturn(true);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        ProductDto result = productService.updateProduct(productId, updatedProductDto);

        assertEquals("Updated Product", result.getName());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProduct(productId);

        // You can add additional assertions based on your specific requirements
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteProductNotFound() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });

        // You can add additional assertions based on your specific requirements
        verify(productRepository, never()).deleteById(productId);
    }
}
