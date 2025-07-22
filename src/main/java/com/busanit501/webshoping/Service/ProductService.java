package com.busanit501.webshoping.Service;

import com.busanit501.webshoping.ResponseDto.ProductResponseDto;
import com.busanit501.webshoping.RequestDto.ProductRequestDto;
import com.busanit501.webshoping.Entity.Product;
import com.busanit501.webshoping.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 전체 상품 목록 조회
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 상품 조회 (ID 기준)
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + id));
        return new ProductResponseDto(product);
    }

    // 상품 등록 기능
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());

        Product saved = productRepository.save(product);
        return new ProductResponseDto(saved);
    }
}
