package com.challenge2.api.models.payload.responses;

import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private int quantity;
    private double price;
}
