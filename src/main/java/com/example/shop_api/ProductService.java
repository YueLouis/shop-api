package com.example.shop_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> topSold() {
        return productRepository.findTop10ByOrderByQuantitySoldDesc();
    }

    public List<Product> newestIn7Days() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return productRepository.findTop10ByCreatedAtAfterOrderByCreatedAtDesc(sevenDaysAgo);
    }

    public Product create(Long categoryId, Product product) {
        Category category = categoryService.findById(categoryId);
        product.setId(null);
        product.setCategory(category);
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(LocalDateTime.now());
        }
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updated.getName());
        existing.setQuantitySold(updated.getQuantitySold());
        if (updated.getCreatedAt() != null) {
            existing.setCreatedAt(updated.getCreatedAt());
        }
        if (updated.getCategory() != null && updated.getCategory().getId() != null) {
            Category newCat = categoryService.findById(updated.getCategory().getId());
            existing.setCategory(newCat);
        }
        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
