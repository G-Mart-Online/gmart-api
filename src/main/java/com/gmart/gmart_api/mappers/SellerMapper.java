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

    public SellerResponseDto toSellerResponseDto(User user, Seller seller) {
        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setUserId(user.getUserId());
        sellerResponseDto.setFirstname(user.getFirstName());
        sellerResponseDto.setLastname(user.getLastName());
        sellerResponseDto.setEmail(user.getEmail());
        sellerResponseDto.setDescription(seller.getDescription());
        sellerResponseDto.setSellerStatus(seller.getSellerStatus());
        sellerResponseDto.setSellerCreatedDate(seller.getSellerCreatedDate());
        sellerResponseDto.setSellerUpdatedDate(seller.getSellerUpdatedDate());

        return sellerResponseDto;
    }
}
