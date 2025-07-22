package com.busanit501.webshoping.RequestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // 기본 생성자 추가

// 클라이언트로부터 받아올 상품 정보를 담는 객체
@Getter
@Setter
@NoArgsConstructor // 롬복이 기본 생성자를 만들어줘.
public class ProductRequestDto {
    // 사용자가 입력할 필드만 정의해. productId는 DB에서 자동으로 생성되니 필요 없어.
    private String productName; // 상품 이름
    private int price; // 상품 가격
    private int stock; // 상품 재고 수량
    private String productTag; // 상품 태그

    // DTO를 엔티티로 변환하는 메서드를 여기에 추가할 수도 있어.
    // 하지만 서비스 계층에서 변환하는 게 더 일반적이야.
}