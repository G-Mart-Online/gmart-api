package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.dto.sellerDto.SellerRequestDto;
import com.gmart.gmart_api.dto.sellerDto.SellerResponseDto;
import com.gmart.gmart_api.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerServiceImpl sellerService;

    @PostMapping("/{userId}")
    public ResponseEntity<SellerResponseDto> createSeller(@PathVariable("userId") String userId, @RequestBody SellerRequestDto sellerRequestDto) {
        SellerResponseDto sellerResponseDto = sellerService.createSeller(userId, sellerRequestDto);
        return new ResponseEntity<>(sellerResponseDto, HttpStatus.OK);
    }

}
