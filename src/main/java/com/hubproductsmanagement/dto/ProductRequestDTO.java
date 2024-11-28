package com.hubproductsmanagement.dto;

import com.hubproductsmanagement.constant.ProductStatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public final class ProductRequestDTO extends BaseSealedEntityDTO {

	private Long id;

	@NotEmpty
	private String productName;

	@NotEmpty
	private String supplier;


	private String description;

	@NotNull
	private ProductStatusEnum status;

}
