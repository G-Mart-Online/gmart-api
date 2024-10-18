package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private ZonedDateTime created_at;
    private ZonedDateTime last_updated_at;
}
