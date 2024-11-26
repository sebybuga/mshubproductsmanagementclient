package com.hubproductsmanagement.dto;

import com.hubproductsmanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreDTO  {

    private Long id;
    private ProductDTO product;
    private Double quantity;
    private Double price;
    private CurrencyEnum currencyId;


}
