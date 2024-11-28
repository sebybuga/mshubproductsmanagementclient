package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.dto.DeleteResponseREC;
import com.hubproductsmanagement.repo.ProductRepository;
import com.hubproductsmanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("hub-products-management/products")

public class ProductController {
    private ProductService productService;

    private ProductRepository productRepository;

    private Mapper mapper;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDto, @RequestHeader HttpHeaders headers) {
        log.info("Request to create product with params: {} ", productDto);
        return productService.createProduct(productDto);
    }


    @PutMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDto, @RequestHeader HttpHeaders headers) {
        log.info("Request to update product with params: {} ", productDto);
        return productService.updateProduct(productDto);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        log.info("Request to select product with id: {} ", id);
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public DeleteResponseREC deleteProduct(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        log.info("Request to delete product with id: {} ", id);
        return productService.deleteProduct(id);
    }

    @GetMapping("/all/{ids}")
    public List<ProductDTO> getProductsByIds(@PathVariable List<Long> ids) {
        log.info("Request to select products with ids: {} ", ids);
        return productRepository.findAllById(ids)
                .stream()
                .map(productEntity -> mapper.map(productEntity, ProductDTO.class))
                .toList();


    }


}
