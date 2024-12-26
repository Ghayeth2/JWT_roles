package com.challenge2.api.models.payload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    @NotBlank(message = "this field is required")
    private String name;
    private int quantity;
    private double price;
}
