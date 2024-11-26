package com.hubproductsmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreRequestDTO {

    @NonNull
    private Long productId;

    @NonNull
    private Double quantity;


}
