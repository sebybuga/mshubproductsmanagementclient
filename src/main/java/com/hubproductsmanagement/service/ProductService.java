package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private ProductRepository productRepository;



    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }

    public ProductDTO createProduct(ProductDTO productDTO) {

        return saveProductInDatabase(productDTO);

    }

    @Transactional
    private ProductDTO saveProductInDatabase(ProductDTO productDTO) {
        log.info("productEntity to be saved is :{}", productDTO);

        ProductEntity productEntity = null;
        ProductDTO savedProductDTO = null;

        try {

            Long productId = productDTO.getId();
            if (productId != null) {
                Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
                if (productEntityOptional.isEmpty()) {
                    throw new Exception("Product id not found!");
                }

            }

            productEntity = mapper.map(productDTO, ProductEntity.class);

            ProductEntity savedProduct = productRepository.save(productEntity);

            savedProductDTO = mapper.map(savedProduct, ProductDTO.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return savedProductDTO;
    }


    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = null;
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (productEntity.isPresent()) {
            productDTO = mapper.map(productEntity.get(), ProductDTO.class);
        }

        return productDTO;

    }


    public ProductDTO updateProduct(ProductDTO productDto) {

        return saveProductInDatabase(productDto);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

}
