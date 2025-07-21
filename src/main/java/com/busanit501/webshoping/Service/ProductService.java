package com.busanit501.webshoping.Service;

import com.busanit501.webshoping.ResponseDto.ProductResponseDto;
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

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProduct(Long id) {
        return new ProductResponseDto(productRepository.findById(id).orElseThrow());
    }
}
