package com.busanit501.webshoping.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime; // 리뷰 작성 시간을 저장하기 위함

@Entity // 나 데이터베이스 테이블이랑 연결될 놈이야!
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews") // 리뷰 테이블 이름을 "reviews"로 명시
public class Review {

    @Id // 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동 생성
    private Long reviewId; // 리뷰 고유 번호

    private String reviewContent; // 리뷰 내용
    private int rating; // 별점 (예: 1~5점)

    // 리뷰 작성 시간. @CreationTimestamp나 @UpdateTimestamp는 하이버네이트 전용이라
    // Spring Data JPA에서 더 범용적인 @Column(updatable = false)를 사용하고
    // @PrePersist를 이용해 직접 시간 설정하는 게 일반적이야.
    // 여기서는 간단하게 LocalDateTime으로 할게.
    @Column(nullable = false, updatable = false) // null 불가능, 업데이트 불가능
    private LocalDateTime createdAt; // 리뷰 작성 시간

    // ✨ 중요! ✨
    // Product 엔티티와 연관 관계 설정: Review는 특정 Product에 속해.
    // ManyToOne: 여러 개의 Review가 하나의 Product에 연결될 수 있어.
    // FetchType.LAZY: Product 정보를 당장 필요할 때만 로딩해. (성능 최적화)
    //                롤에서 아이템 정보가 바로 필요한 게 아니면 굳이 안 불러오는 것과 비슷해.
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn: 외래 키(Foreign Key)를 매핑하는 컬럼을 지정.
    //              review_product_id 컬럼이 Product 테이블의 productId를 참조하게 돼.
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 이 리뷰가 어떤 상품에 대한 리뷰인지 가리킴

    // 엔티티가 저장되기 전에 자동으로 호출되어 createdAt 필드에 현재 시간을 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}