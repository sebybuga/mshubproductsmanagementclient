package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.config.UserConfig;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.repo.ProductRepository;
import com.hubproductsmanagement.service.AuthenticationService;
import com.hubproductsmanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("hub-products-management/products")
public class ProductController {
	private ProductService productService;

	private AuthenticationService authService;
	private ProductRepository productRepository;
	private UserConfig userConfig;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public ProductController(ProductService productService, ProductRepository productRepository, UserConfig userConfig) {
		this.productService = productService;
		this.productRepository=productRepository;
		this.userConfig = userConfig;
	}

	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDto, @RequestHeader HttpHeaders headers) {
		log.info("Request to create product with params: {} ",productDto);

		try {
			if (!authService.authenticate(headers))
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

			return productService.createProduct(productDto, headers);

		} catch (Exception e) {
			log.info("An error has occurred while updating product with params: {}, error:{}  "
					,productDto, e);
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}



	@PutMapping
	//@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDto, @RequestHeader HttpHeaders headers) {
		log.info("Request to update product with params: {} ",productDto);
			try {
				if (!authService.authenticate(headers)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
				if (productDto.getId() == null) throw new Exception("Product id not provided!");
				return productService.updateProduct(productDto, headers);

			} catch (Exception e) {
				log.info("An error has occurred while updating product with params: {}, error:{}  "
						,productDto, e);
				return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}

	@GetMapping("/{id}")
	//@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'STAFF')")
	public ProductDTO getProduct(@PathVariable Long id) {
		log.info("Request to select product with id: {} ",id);
		return productService.getProduct(id);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAnyRole('ADMINISTRATOR')")
	public void deleteProduct(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		log.info("Request to delete product with id: {} ",id);
		productService.deleteProduct(id, headers);
	}
	
	@GetMapping("/all/{ids}")
	//@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'STAFF')")
	public List<ProductDTO> getProductsByIds(@PathVariable List<Long> ids){
		log.info("Request to select products with ids: {} ",ids);
		return productRepository.findAllById(ids)
				.stream()				
                .map(productEntity -> mapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
