package com.challenge2.api.controllers;

import com.challenge2.api.models.entities.Product;
import com.challenge2.api.models.payload.requests.ProductRequest;
import com.challenge2.api.services.abstracts.ProductServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductServices productServices;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void productController_GetAllProducts() {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void secureEndpoint_AccessDenied() {
        ProductRequest pr = ProductRequest.builder().name("name").build();
        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(pr)))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @SneakyThrows
    @WithMockUser( authorities = {"ROLE_USER"})
    void productController_SaveProduct() {
        ProductRequest product = ProductRequest.builder().name("name").price(4).quantity(3)
                        .build();
        BDDMockito.given(productServices.save(ArgumentMatchers.any()))
                .willReturn("Created");
        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(product)))
        .andExpect(MockMvcResultMatchers.status().isCreated());


    }

    @Test
    @SneakyThrows
    @WithMockUser(authorities = {"ROLE_USER"})
    void productController_UpdateProduct() {
        ProductRequest product = ProductRequest.builder().name("name").price(3).quantity(3)
                .build();
        BDDMockito.given(productServices.update(ArgumentMatchers.any()
        , ArgumentMatchers.anyLong())).willReturn("Updated");
        mockMvc.perform(MockMvcRequestBuilders.put("/products/put")
        .contentType(MediaType.APPLICATION_JSON)
        .param("id", "1")
        .content(objectMapper.writeValueAsString(product)))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void productController_DeleteProduct() {
        BDDMockito.given(productServices.delete(ArgumentMatchers.anyLong()))
                .willReturn("Deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete"
        ).param("id", "1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

}