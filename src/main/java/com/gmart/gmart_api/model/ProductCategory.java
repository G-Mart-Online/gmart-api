package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private Instant created_at;
    private Instant last_updated_at;
}
