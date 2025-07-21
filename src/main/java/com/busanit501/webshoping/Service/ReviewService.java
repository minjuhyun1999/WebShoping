package com.busanit501.webshoping.Service;

import com.busanit501.webshoping.ResponseDto.ReviewResponseDto;
import com.busanit501.webshoping.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewResponseDto> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}

