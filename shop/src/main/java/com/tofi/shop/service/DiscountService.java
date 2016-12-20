package com.tofi.shop.service;

import com.tofi.shop.domain.User;

public interface DiscountService {
    int getDiscount(User user, long totalAmount);
}
