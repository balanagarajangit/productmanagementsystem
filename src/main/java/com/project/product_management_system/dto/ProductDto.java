package com.project.product_management_system.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull(message = "Product ID cannot be null")
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message = "Product price cannot be null")
    private Double price;
    private String description;
    private int quantity;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductDto() {
        super();
    }

    public ProductDto(Long id, String name, String description, double price, int quantity, LocalDate startDate,
                     LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
}
