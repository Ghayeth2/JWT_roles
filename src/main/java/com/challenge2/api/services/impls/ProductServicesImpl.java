package com.challenge2.api.services.impls;

import com.challenge2.api.models.entities.Product;
import com.challenge2.api.models.payload.requests.ProductRequest;
import com.challenge2.api.models.payload.responses.ProductResponse;
import com.challenge2.api.repos.ProductRepo;
import com.challenge2.api.services.abstracts.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {
    private final ProductRepo productRepo;
    @Override
    public String save(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepo.save(product);
        return "Product saved successfully";
    }

    @Override
    public String update(ProductRequest productRequest, Long id) {
        Product product = productRepo.findById(id).orElseThrow();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        productRepo.save(product);
        return "Product updated successfully";
    }

    @Override
    public String delete(Long id) {
        Product product = productRepo.findById(id).orElseThrow();
        productRepo.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public List<ProductResponse> products() {
        return productRepo.findAll().stream()
                .map(
                        product -> {
                            System.out.println(product.getId()
                            + " " + product.getName());
                            return ProductResponse
                                .builder()
                                .name(product.getName())
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .build();
                            }
                ).toList();
    }
}
