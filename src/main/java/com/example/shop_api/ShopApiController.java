package com.example.shop_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopApiController {

    private final CategoryService categoryService;
    private final ProductService productService;

    // ===== CATEGORY CRUD =====
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    // ===== PRODUCT CRUD + CÁC API ĐỀ BÀI =====
    @GetMapping("/categories/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return productService.findByCategory(id);
    }

    @PostMapping("/categories/{categoryId}/products")
    public Product createProduct(@PathVariable Long categoryId,
                                 @RequestBody Product product) {
        return productService.create(categoryId, product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/products/top-sold")
    public List<Product> getTopSoldProducts() {
        return productService.topSold();
    }

    @GetMapping("/products/new")
    public List<Product> getNewestProducts() {
        return productService.newestIn7Days();
    }
}
