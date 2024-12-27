package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Product;
import com.challenge2.api.models.payload.requests.ProductRequest;
import com.challenge2.api.models.payload.responses.ProductResponse;
import com.challenge2.api.repos.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServicesImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServicesImpl productServices;


    @Test
    void productServices_SaveProduct_ReturnsString() {
        ProductRequest request = ProductRequest.builder().name("Test").build();
        Product product = Product.builder().price(4000).name("Test").build();
        when(productRepo.save(any())).thenReturn(product);
        String res = productServices.save(request);
        assertEquals(res, "Product saved successfully");
    }

    @Test
    void productServices_UpdateProduct_ReturnsString() {
        ProductRequest request = ProductRequest.builder().name("Test").build();
        Product product = Product.builder().price(4000).name("Test").build();
        when(productRepo.findById(anyLong()))
                .thenReturn(Optional.of(product));
        when(productRepo.save(any()))
                .thenReturn(product);
        String res = productServices.update(request, 1L);
        assertEquals(res, "Product updated successfully");
    }

    @Test
    void productServices_DeleteProduct_ReturnsString() {
        Product product = Product.builder().price(4000).name("Test").build();
        when(productRepo.findById(anyLong()))
                .thenReturn(Optional.of(product));
        doNothing().when(productRepo).delete(any());
        String res = productServices.delete(1L);
        assertEquals(res, "Product deleted successfully");
    }

    @Test
    void productServices_FindAll_ReturnsProducts() {
        Product response = Product.builder().name("Test").build();
        List<Product> products = List.of(response);
        when(productRepo.findAll()).thenReturn(products);
        List<ProductResponse> res = productServices.products();
        Assertions.assertThat(res).size().isEqualTo(1);
    }

}