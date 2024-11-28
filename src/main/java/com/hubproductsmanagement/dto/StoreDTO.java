package com.hubproductsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreDTO  {

    private Long id;
    private String storeName;
    private String zipCode;
    private String address;
    private String city;
    private String country;

    private  List<ProductStoreDTO> storeProductList;


}
