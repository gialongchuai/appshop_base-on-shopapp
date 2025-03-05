package com.gialongchuai.shopapp.constants;

public class SecurityConstant {
    public static final String[] PUBLIC_ENDPOINTS = {
            "/categories", "/categories/{categoryId}",
            "/products", "/products/{productId}", "pr",
            "/users", "/users/registration",  "/users/{userId}",
            "/roles",
            "/auth/token", "auth/introspect", "/auth/logout", "/auth/refresh",
    };

    public static final String JWT_AUTHORITY_PREFIX = "";
    public static final int BCRYPT_STRENGTH = 10;
}
