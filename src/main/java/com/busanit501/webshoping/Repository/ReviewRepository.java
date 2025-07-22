package com.busanit501.webshoping.Repository;

import com.busanit501.webshoping.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // ❌ 기존 코드: No property 'id' found for type 'Product' 에러 발생
    // List<Review> findByProductId(Long productId);

    // ✅ 수정된 코드: Product 엔티티의 'productId' 필드를 기준으로 찾아라!
    // findBy[관계_엔티티_필드명][연결된_엔티티_ID_필드명] 규칙을 따릅니다.
    // Review 엔티티는 product 필드를 가지고 있고, Product 엔티티의 ID는 productId입니다.
    // 그래서 findByProduct_ProductId 입니다!
    List<Review> findByProduct_ProductId(Long productId);
}