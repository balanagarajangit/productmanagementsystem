package com.project.product_management_system.mapper;

import com.project.product_management_system.dto.ProductDto;
import com.project.product_management_system.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getStartDate(),
                product.getEndDate()
        );
    }

    public static Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getQuantity(),
                productDto.getStartDate(),
                productDto.getEndDate()
        );
    }
}