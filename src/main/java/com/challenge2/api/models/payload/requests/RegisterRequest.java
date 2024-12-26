package com.challenge2.api.models.payload.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "this field is required")
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    private String fullName;
    @NotBlank(message = "this field is required")
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    private String password;
    @NotBlank(message = "this field is required")
    @NotNull(message = "this field is required")
    @NotEmpty(message = "this field is required")
    @Email(message = "enter formatted email")
    private String email;

}
