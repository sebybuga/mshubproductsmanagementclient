package com.hubproductsmanagement.dto;

import com.hubproductsmanagement.constant.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProductDTO {

	private Long id;

	private String productName;

	private String supplier;

	private String description;

	private ProductStatusEnum status;

}
