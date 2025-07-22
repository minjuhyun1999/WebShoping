package com.busanit501.webshoping.ResponseDto;

import com.busanit501.webshoping.Entity.Review; // Review 엔티티 import
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime; // 작성 시간

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long reviewId; // 리뷰 고유 번호
    private String reviewContent; // 리뷰 내용
    private int rating; // 별점
    private LocalDateTime createdAt; // 리뷰 작성 시간
    private Long productId; // 어떤 상품에 대한 리뷰인지 상품 ID도 함께 보냄

    // Review 엔티티를 ReviewResponseDto로 변환하는 생성자
    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.reviewContent = review.getReviewContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt();
        // 연관된 Product가 있다면 그 Product의 ID를 가져옴. (Null 방지)
        this.productId = (review.getProduct() != null) ? review.getProduct().getProductId() : null;
    }
}