package com.gmart.gmart_api.dto.sellerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDto {
    private String description;
    private String sellerStatus;
}
