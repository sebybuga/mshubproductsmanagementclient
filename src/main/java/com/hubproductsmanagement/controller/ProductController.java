package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.config.UserConfig;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.repo.ProductRepository;
import com.hubproductsmanagement.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("hub-products-management/api/products")
public class ProductController {
	private ProductService productService;
	private ProductRepository productRepository;
	private UserConfig userConfig;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public ProductController(ProductService productService, ProductRepository productRepository, UserConfig userConfig) {
		this.productService = productService;
		this.productRepository=productRepository;
		this.userConfig = userConfig;
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDto) {
		log.info("Request to create product with params: {} ",productDto);
		return productService.createProduct(productDto);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDto) {
		log.info("Request to update product with params: {} ",productDto);
			try {
				if (productDto.getId() == null) throw new Exception("Product id not provided!");
				return productService.updateProduct(productDto);

			} catch (Exception e) {
				log.info("An error has occured while updating product with params: {} ",productDto);
				throw new RuntimeException(e);
			}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'STAFF')")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteProduct(@PathVariable Long id) {
		log.info("Request to delete product with id: {} ",id);
		productService.deleteProduct(id);
	}
	
	@GetMapping("/all/{ids}")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'STAFF')")
	public List<ProductDTO> getProductsByIds(@PathVariable List<Long> ids){

		return productRepository.findAllById(ids)
				.stream()				
                .map(productEntity -> mapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
