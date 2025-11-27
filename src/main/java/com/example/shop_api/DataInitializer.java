package com.example.shop_api;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) return;

        Category phone = categoryRepository.save(
                Category.builder().name("Điện thoại").build()
        );
        Category laptop = categoryRepository.save(
                Category.builder().name("Laptop").build()
        );

        LocalDateTime now = LocalDateTime.now();

        productRepository.saveAll(List.of(
                Product.builder().name("iPhone 15")
                        .quantitySold(200)
                        .createdAt(now.minusDays(2))
                        .category(phone).build(),
                Product.builder().name("Galaxy S24")
                        .quantitySold(150)
                        .createdAt(now.minusDays(5))
                        .category(phone).build(),
                Product.builder().name("Redmi Note")
                        .quantitySold(80)
                        .createdAt(now.minusDays(10))
                        .category(phone).build(),
                Product.builder().name("MacBook Air")
                        .quantitySold(120)
                        .createdAt(now.minusDays(1))
                        .category(laptop).build(),
                Product.builder().name("Asus ROG")
                        .quantitySold(90)
                        .createdAt(now.minusDays(3))
                        .category(laptop).build()
        ));
    }
}
