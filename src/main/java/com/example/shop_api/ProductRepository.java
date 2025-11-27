package com.example.shop_api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // tất cả sản phẩm theo từng danh mục
    List<Product> findByCategoryId(Long categoryId);

    // 10 sản phẩm bán nhiều nhất
    List<Product> findTop10ByOrderByQuantitySoldDesc();

    // 10 sản phẩm được tạo trong <= 7 ngày
    List<Product> findTop10ByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime dateTime);
}
