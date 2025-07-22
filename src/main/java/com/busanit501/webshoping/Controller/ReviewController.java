package com.busanit501.webshoping.Controller;

import com.busanit501.webshoping.RequestDto.ReviewRequestDto; // 요청 DTO
import com.busanit501.webshoping.ResponseDto.ReviewResponseDto; // 응답 DTO
import com.busanit501.webshoping.Service.ReviewService; // 리뷰 서비스
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 나 REST API 컨트롤러야!
// 이 컨트롤러는 "/products/{productId}/reviews" 경로로 들어오는 요청을 처리해.
// 특정 상품에 대한 리뷰 기능을 제공한다는 걸 명확히 하기 위해 경로에 productId를 포함했어.
// 롤에서 특정 챔피언에 대한 '아이템 빌드' 정보는 그 챔피언 페이지 안에서 보는 것과 같아.
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 생성자 주입
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ✅ 리뷰 등록 (CREATE)
    // POST /products/{productId}/reviews
    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable Long productId, @RequestBody ReviewRequestDto requestDto) {
        // @PathVariable Long productId: URL 경로에서 상품 ID를 받아와.
        // @RequestBody ReviewRequestDto requestDto: 요청 본문에서 리뷰 내용을 받아와.
        ReviewResponseDto responseDto = reviewService.createReview(productId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // ✅ 특정 상품의 모든 리뷰 조회 (READ ALL)
    // GET /products/{productId}/reviews
    @GetMapping
    public List<ReviewResponseDto> getAllReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getAllReviewsByProductId(productId);
    }

    // ✅ 특정 리뷰 조회 (READ ONE)
    // GET /products/{productId}/reviews/{reviewId}
    // (여기서 productId는 굳이 필요 없지만, RESTful URL 계층 구조를 위해 남겨두는 경우가 많아.)
    @GetMapping("/{reviewId}")
    public ReviewResponseDto getReview(@PathVariable Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    // ✅ 리뷰 수정 (UPDATE)
    // PUT /products/{productId}/reviews/{reviewId}
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto requestDto) {
        // 어떤 리뷰(reviewId)를 어떤 내용(requestDto)으로 수정할지 받아서 서비스에 전달.
        ReviewResponseDto updatedReview = reviewService.updateReview(reviewId, requestDto);
        return ResponseEntity.ok(updatedReview);
    }

    // ✅ 리뷰 삭제 (DELETE)
    // DELETE /products/{productId}/reviews/{reviewId}
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}