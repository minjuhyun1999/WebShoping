package com.busanit501.webshoping.Controller;

import com.busanit501.webshoping.RequestDto.ProductRequestDto;
import com.busanit501.webshoping.ResponseDto.ProductResponseDto;
import com.busanit501.webshoping.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ 상품 등록 (POST)
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.createProduct(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // ✅ 전체 상품 조회 (GET)
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ 특정 상품 조회 (GET /products/{id})
    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}
