package com.busanit501.webshoping.RequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private int price;

    public ProductRequestDto(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
