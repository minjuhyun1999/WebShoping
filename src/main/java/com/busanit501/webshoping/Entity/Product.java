package com.busanit501.webshoping.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList; // 리스트를 사용하기 위함
import java.util.List; // 리스트를 사용하기 위함

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private int price;
    private int stock;
    private String productTag;

    // 기존 재고 관리 메서드
    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + this.stock);
        }
        this.stock = restStock;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    // ✨ 중요! ✨
    // Review 엔티티와 연관 관계 설정: 하나의 Product는 여러 개의 Review를 가질 수 있어.
    // OneToMany: 하나의 Product가 여러 Review에 연결될 수 있어.
    // mappedBy = "product": Review 엔티티의 'product' 필드에 의해 매핑된다는 의미야.
    //                       Review 쪽이 관계의 '주인'이라는 뜻! (외래 키를 Review가 가지고 있으니)
    // CascadeType.ALL: Product가 변경(삭제 등)되면 연관된 Review도 함께 변경(삭제)돼.
    //                  롤에서 챔피언이 죽으면 챔피언이 가진 모든 버프가 사라지는 것과 비슷해.
    // orphanRemoval = true: Product에서 Review를 끊으면(리스트에서 제거하면) 해당 Review도 DB에서 삭제돼.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>(); // 해당 상품에 달린 리뷰 목록
    // 초기화 해주는 게 NullPointerException 방지에 좋아.

    // 편의 메서드: Product에 Review를 추가할 때 사용하는 메서드
    // 양방향 관계 설정 시, 두 엔티티 객체의 관계를 항상 동기화 해주는 게 중요해!
    public void addReview(Review review) {
        this.reviews.add(review);
        review.setProduct(this); // Review 쪽에도 Product 설정
    }

    // 편의 메서드: Product에서 Review를 제거할 때 사용하는 메서드
    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setProduct(null); // Review 쪽에서 Product 연결 끊기
    }
}