package com.busanit501.webshoping.Controller;

import com.busanit501.webshoping.RequestDto.ProductRequestDto; // 클라이언트가 보내는 상품 정보 DTO
import com.busanit501.webshoping.ResponseDto.ProductResponseDto; // 서버가 클라이언트에게 보내는 상품 정보 DTO
import com.busanit501.webshoping.Service.ProductService; // 비즈니스 로직을 처리하는 서비스 계층
import org.springframework.http.HttpStatus; // HTTP 상태 코드를 사용하기 위한 클래스
import org.springframework.http.ResponseEntity; // HTTP 응답 전체 (상태 코드, 헤더, 바디)를 표현하는 클래스
import org.springframework.web.bind.annotation.*; // RESTful API 어노테이션들

import java.util.List; // 여러 상품을 리스트로 다룰 때 사용

@RestController // "나 REST API 컨트롤러야! HTTP 응답 본문으로 데이터를 직접 보낼게!" 라고 알려줘.
@RequestMapping("/products") // 이 컨트롤러의 모든 엔드포인트는 "/products"로 시작해. (예: /products, /products/1)
public class ProductController {

    private final ProductService productService; // ProductService를 주입받아 사용.

    // 생성자 주입: 스프링이 ProductService 객체를 자동으로 연결해줘. (DI)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ 상품 등록 (CREATE - POST 요청)
    // POST /products
    @PostMapping // HTTP POST 요청을 처리.
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        // @RequestBody: 클라이언트가 보낸 JSON 데이터를 ProductRequestDto 객체로 바꿔줘.
        ProductResponseDto responseDto = productService.createProduct(requestDto);
        // HTTP 상태 코드 201 (Created) 와 함께 생성된 상품 정보를 응답.
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // ✅ 전체 상품 조회 (READ ALL - GET 요청)
    // GET /products
    @GetMapping // HTTP GET 요청을 처리.
    public List<ProductResponseDto> getAllProducts() {
        // 모든 상품 리스트를 서비스로부터 받아서 반환.
        // @RestController 덕분에 List<ProductResponseDto> 객체가 자동으로 JSON 배열로 변환되어 응답돼.
        return productService.getAllProducts();
    }

    // ✅ 특정 상품 조회 (READ ONE - GET 요청, Path Variable 사용)
    // GET /products/{id} (예: /products/1, /products/123)
    @GetMapping("/{id}") // URL 경로의 {id} 부분을 받아와.
    public ProductResponseDto getProduct(@PathVariable Long id) {
        // @PathVariable: URL 경로에서 변수(id)를 추출해줘. 롤에서 특정 챔피언의 ID를 입력해서 정보를 조회하는 느낌!
        return productService.getProduct(id);
    }

    // ✅ 상품 수정 (UPDATE - PUT 요청)
    // PUT /products/{id}
    @PutMapping("/{id}") // HTTP PUT 요청을 처리.
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        // 어떤 상품(id)을 어떤 내용(requestDto)으로 수정할지 받아서 서비스에 전달.
        ProductResponseDto updatedProduct = productService.updateProduct(id, requestDto);
        // HTTP 상태 코드 200 (OK) 와 함께 수정된 상품 정보를 응답.
        return ResponseEntity.ok(updatedProduct);
    }

    // ✅ 상품 삭제 (DELETE - DELETE 요청)
    // DELETE /products/{id}
    @DeleteMapping("/{id}") // HTTP DELETE 요청을 처리.
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // 어떤 상품(id)을 삭제할지 받아서 서비스에 전달.
        productService.deleteProduct(id);
        // HTTP 상태 코드 204 (No Content) 응답. 성공적으로 삭제했지만, 클라이언트에게 돌려줄 데이터는 없어.
        return ResponseEntity.noContent().build();
    }
}