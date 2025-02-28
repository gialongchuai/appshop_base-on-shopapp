package com.gialongchuai.shopapp.constants;

public class SecurityConstant {
    public static final String[] PUBLIC_ENDPOINTS = {
            "/categories", "/categories/{categoryId}",
            "/products", "/products/{productId}", "pr"
    };

    public static final String JWT_AUTHORITY_PREFIX = "";
    public static final int BCRYPT_STRENGTH = 10;
}
