package com.busanit501.webshoping.Service;

import com.busanit501.webshoping.Entity.Product;
import com.busanit501.webshoping.Entity.Review;
import com.busanit501.webshoping.Repository.ProductRepository;
import com.busanit501.webshoping.Repository.ReviewRepository;
import com.busanit501.webshoping.RequestDto.ReviewRequestDto;
import com.busanit501.webshoping.ResponseDto.ReviewResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    // ✅ 리뷰 등록 (Create Review for a Product)
    public ReviewResponseDto createReview(Long productId, ReviewRequestDto requestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. 상품 ID: " + productId));

        Review review = new Review();
        review.setReviewContent(requestDto.getReviewContent());
        review.setRating(requestDto.getRating());
        review.setProduct(product);

        product.addReview(review);

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(savedReview);
    }

    // ✅ 특정 상품의 모든 리뷰 조회 (Read All Reviews for a Product)
    public List<ReviewResponseDto> getAllReviewsByProductId(Long productId) {
        // ❌ 기존 코드: List<Review> reviews = reviewRepository.findByProductId(productId);
        //             'findByProductId' 메서드는 더 이상 ReviewRepository에 존재하지 않습니다.

        // ✅ 수정된 코드: ReviewRepository에서 변경된 메서드 이름으로 호출해야 합니다.
        List<Review> reviews = reviewRepository.findByProduct_ProductId(productId); // <-- 이 부분을 수정했습니다!

        return reviews.stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    // ✅ 특정 리뷰 조회 (Read One Review)
    public ReviewResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. 리뷰 ID: " + reviewId));
        return new ReviewResponseDto(review);
    }

    // ✅ 리뷰 수정 (Update Review)
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. 리뷰 ID: " + reviewId));

        review.setReviewContent(requestDto.getReviewContent());
        review.setRating(requestDto.getRating());

        return new ReviewResponseDto(review);
    }

    // ✅ 리뷰 삭제 (Delete Review)
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("삭제할 리뷰를 찾을 수 없습니다. 리뷰 ID: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}