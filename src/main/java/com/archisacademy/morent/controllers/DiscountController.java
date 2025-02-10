package com.archisacademy.morent.controllers;


import com.archisacademy.morent.dtos.requests.ApplyDiscountRequest;
import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.ActiveDiscountResponse;
import com.archisacademy.morent.dtos.responses.ApplyDiscountResponse;
import com.archisacademy.morent.dtos.responses.DiscountResponse;
import com.archisacademy.morent.services.abstracts.DiscountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResponse> createDiscount(@RequestBody CreateDiscountRequest request) {
        DiscountResponse response = discountService.createDiscount(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplyDiscountResponse> applyDiscount(@RequestBody ApplyDiscountRequest request) {
        ApplyDiscountResponse response = discountService.applyDiscount(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ActiveDiscountResponse>> getActiveDiscounts() {
        List<ActiveDiscountResponse> discounts = discountService.getActiveDiscounts();
        return ResponseEntity.ok(discounts);
    }
}
