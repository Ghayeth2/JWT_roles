package com.challenge2.api.controllers;

import com.challenge2.api.models.payload.requests.LoginRequest;
import com.challenge2.api.models.payload.requests.RegisterRequest;
import com.challenge2.api.models.payload.responses.LoginResponse;
import com.challenge2.api.services.abstracts.AuthServices;
import com.challenge2.api.services.abstracts.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServices userServices;

    @Mock
    private AuthServices authServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void authController_SignUP() {
        RegisterRequest request = RegisterRequest.builder().email("test@gmail.com")
                        .password("pass").fullName("ful").build();
        BDDMockito.given(userServices.save(ArgumentMatchers.any()))
                .willReturn("Created");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @SneakyThrows
    void authController_Login() {
        LoginRequest lReq = LoginRequest.builder().email("test@gmail.com")
                        .password("pass").build();
        LoginResponse lRes = LoginResponse.builder().token("token").build();
        BDDMockito.given(authServices.login(ArgumentMatchers.any()))
                .willReturn(lRes);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(lReq)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
        .string(Matchers.containsStringIgnoringCase("token")));
    }


}