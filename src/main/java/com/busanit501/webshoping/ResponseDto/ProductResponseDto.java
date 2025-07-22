package com.busanit501.webshoping.ResponseDto;

import com.busanit501.webshoping.Entity.Product; // Product 엔티티를 import
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // 기본 생성자 추가

// 클라이언트에 응답으로 보낼 상품 정보를 담는 객체
@Getter
@Setter
@NoArgsConstructor // 롬복이 기본 생성자를 만들어줘.
public class ProductResponseDto {
    private Long productId; // 상품 고유 번호
    private String productName; // 상품 이름
    private int price; // 상품 가격
    private int stock; // 상품 재고 수량
    private String productTag; // 상품 태그

    // 엔티티(Product) 객체를 DTO(ProductResponseDto)로 변환하는 생성자.
    // 이걸 만들면 Product 엔티티를 바로 ResponseDto로 바꿀 수 있어서 편해!
    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.productTag = product.getProductTag();
    }
}