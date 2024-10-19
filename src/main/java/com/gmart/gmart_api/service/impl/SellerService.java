package com.gmart.gmart_api.service.impl;

import com.gmart.gmart_api.dto.SellerRequestDto;
import com.gmart.gmart_api.dto.SellerResponseDto;

import java.util.List;

public interface SellerService {
    SellerResponseDto createSeller(String userId, SellerRequestDto sellerRequestDto);
    SellerResponseDto getSellerById(String userId);
    SellerResponseDto updateSeller(String userId, SellerRequestDto sellerRequestDto);
    List<SellerResponseDto> getAllSellers();
    void deleteSeller(String userId);
}
