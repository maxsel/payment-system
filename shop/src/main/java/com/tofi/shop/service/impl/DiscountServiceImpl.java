package com.tofi.shop.service.impl;

import com.tofi.shop.domain.User;
import com.tofi.shop.service.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService{
    final int hiAmount = 60;
    final int medAmount = 25;
    final int lowAmount = 15;

    final int hiDiscount = 30;
    final int medDiscount = 20;
    final int lowDiscount = 10;

    @Override
    public int getDiscount(User user, long totalAmount) {
        if (totalAmount > hiAmount) return hiDiscount + user.getDiscount();
        if (totalAmount > medAmount) return medDiscount + user.getDiscount();
        if (totalAmount > lowAmount) return lowDiscount + user.getDiscount();
        return user.getDiscount();
    }
}
