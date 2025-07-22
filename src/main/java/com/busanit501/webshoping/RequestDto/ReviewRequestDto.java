package com.busanit501.webshoping.RequestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private String reviewContent; // 리뷰 내용
    private int rating; // 별점 (예: 1~5점)
    // private Long productId; // 어떤 상품에 대한 리뷰인지 ID를 받을 수도 있지만,
    // 보통 URL의 PathVariable로 받으니 여기선 필요 없어.
}