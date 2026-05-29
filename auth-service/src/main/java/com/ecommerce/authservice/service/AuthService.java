package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.LoginRequest;
import com.ecommerce.authservice.dto.LoginResponse;
import com.ecommerce.authservice.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    
    LoginResponse login(LoginRequest request);

}