package com.busanit501.webshoping.Repository;

import com.busanit501.webshoping.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
