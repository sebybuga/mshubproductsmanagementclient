package com.hubproductsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreRequestDTO {

    private Long id;


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

    private String createdBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "ro-RO", timezone = "Europe/Bucharest")
    private LocalDateTime createdAt;

    private String updatedBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "ro-RO", timezone = "Europe/Bucharest")
    private LocalDateTime updatedAt;

    @NotEmpty
    @Valid
    private List<ProductStoreRequestDTO> storeProductList;


}
