package com.challenge2.api.models.payload.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "this field is required")
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    @Email(message = "enter email formatted text")
    private String email;
    @NotBlank(message = "this field is required")
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    private String password;

}
