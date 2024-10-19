package com.gmart.gmart_api.mappers;

import com.gmart.gmart_api.dto.sellerDto.SellerRequestDto;
import com.gmart.gmart_api.dto.sellerDto.SellerResponseDto;
import com.gmart.gmart_api.model.Seller;
import com.gmart.gmart_api.model.User;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {
    public Seller toSeller(SellerRequestDto sellerRequestDto) {
        Seller seller = new Seller();
        seller.setDescription(sellerRequestDto.getDescription());
        seller.setSellerStatus(sellerRequestDto.getSellerStatus());
        return seller;
    }

    public SellerResponseDto toSellerResponseDto(Seller seller) {
        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setUserId(seller.getUserId());
        sellerResponseDto.setFirstname(seller.getFirstName());
        sellerResponseDto.setLastname(seller.getLastName());
        sellerResponseDto.setEmail(seller.getEmail());
        sellerResponseDto.setCreatedDate(seller.getCreatedDate());
        sellerResponseDto.setLastUpdatedDate(seller.getLastUpdatedDate());
        sellerResponseDto.setDescription(seller.getDescription());
        sellerResponseDto.setSellerStatus(seller.getSellerStatus());
        return sellerResponseDto;
    }
}
