package com.busanit501.webshoping.Repository;

import com.busanit501.webshoping.Entity.Product; // Product 엔티티 import
import org.springframework.data.jpa.repository.JpaRepository; // JPA 기능을 쉽게 쓸 수 있게 해주는 인터페이스
import org.springframework.stereotype.Repository; // 이 인터페이스가 Repository 역할을 함을 명시 (생략 가능하지만 명시적이면 좋음)

// JpaRepository<[엔티티 타입], [엔티티의 ID 타입]> 을 상속받으면
// 기본적인 CRUD (Create, Read, Update, Delete) 메서드들이 자동으로 제공돼!
// 롤에서 '기본 공격'처럼 없으면 안 되는 기능들이 다 들어있다고 보면 돼.
@Repository // 이 인터페이스가 데이터 접근 계층(Repository Layer)임을 스프링에게 알려줘.
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 만약 JpaRepository가 제공하지 않는 복잡한 쿼리가 필요하면 여기에 메서드를 추가할 수 있어.
    // 예: List<Product> findByProductNameContaining(String keyword); // 상품 이름으로 검색
}