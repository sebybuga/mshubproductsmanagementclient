package com.hubproductsmanagement.dto;

import com.hubproductsmanagement.constant.CurrencyEnum;
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

    private Long storeId;

    @NonNull
    private Double quantity;

    @NonNull
    private Double price;
    @NonNull
    private CurrencyEnum currency;

    ProductDTO product;
    StoreDTO store;


}
