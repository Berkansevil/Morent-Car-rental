package com.archisacademy.morent.services.abstracts;


import com.archisacademy.morent.dtos.requests.ApplyDiscountRequest;
import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.ActiveDiscountResponse;
import com.archisacademy.morent.dtos.responses.ApplyDiscountResponse;
import com.archisacademy.morent.dtos.responses.DiscountResponse;
import java.util.List;


public interface DiscountService {
    DiscountResponse createDiscount(CreateDiscountRequest request);
    ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request);
    List<ActiveDiscountResponse> getActiveDiscounts();
}
