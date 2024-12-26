package com.challenge2.api.services.abstracts;

import com.challenge2.api.models.payload.requests.ProductRequest;
import com.challenge2.api.models.payload.responses.ProductResponse;

import java.util.List;

public interface ProductServices {
    String save(ProductRequest productRequest);
    String update(ProductRequest productRequest, Long id);
    String delete(Long id);
    List<ProductResponse> products();
}
