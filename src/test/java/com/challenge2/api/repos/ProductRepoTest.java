package com.challenge2.api.repos;

import com.challenge2.api.models.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;
    private Product product;
    @BeforeEach
    void setUp() {
        product = Product.builder()
                .name("product").price(340).quantity(2).build();
    }

    @Test
    void productRepo_SaveProduct_ReturnsSavedProduct() {
        Product savedProduct = productRepo.save(product);
        assertThat(savedProduct).isNotNull();
    }

    @Test
    void productRepo_FindAll_ReturnsAllProducts() {
        productRepo.save(product);
        var products = productRepo.findAll();
        assertThat(products).hasSize(1);
    }

    @Test
    void productRepo_UpdateProduct_ReturnsUpdatedProduct() {
        productRepo.save(product);
        Product updatedProduct = productRepo.findById(1L).get();
        updatedProduct.setName("updated");
        Product res = productRepo.save(updatedProduct);
        assertThat(res.getName()).isEqualTo("updated");
    }

    @Test
    void productRepo_DeleteProduct_ReturnsNull() {
        productRepo.save(product);
        productRepo.delete(product);
        Optional<Product> res = productRepo.findById(1L);
        assertThat(res).isEmpty();
    }

}