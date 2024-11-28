package com.hubproductsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class StoreRequestDTO extends BaseSealedEntityDTO {


    @NotBlank
    private String storeName;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;

    @NotEmpty
    @Valid
    private List<ProductStoreRequestDTO> storeProductList;


}
