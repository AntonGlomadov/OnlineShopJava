package com.glomadovanton.shop.exception;

import java.util.function.Supplier;

public class CakeNotFoundException extends RuntimeException {
    public CakeNotFoundException(String message) {
        super(message);
    }

}
