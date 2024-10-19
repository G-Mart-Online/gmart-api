package com.gmart.gmart_api.dto.sellerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDto {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private String sellerStatus;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
