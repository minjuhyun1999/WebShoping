package com.busanit501.webshoping.ResponseDto;

import com.busanit501.webshoping.Entity.Review;

public class ReviewResponseDto {
    private Long id;
    private String content;
    private int rating;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.rating = review.getRating();
    }

    // Getter
}
