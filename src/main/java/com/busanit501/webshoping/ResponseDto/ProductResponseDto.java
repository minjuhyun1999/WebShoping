package com.busanit501.webshoping.ResponseDto;

import com.busanit501.webshoping.Entity.Product;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    // Getter
}
