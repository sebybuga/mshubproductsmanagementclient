package com.hubproductsmanagement.dto;

import com.hubproductsmanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
	private Long id;

	private String name;

	private String supplier;

	private String description;

	@Enumerated(EnumType.ORDINAL)
	private CurrencyEnum currencyId;

	private Double price;

	
}
