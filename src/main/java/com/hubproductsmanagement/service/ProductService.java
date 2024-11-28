package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import com.hubproductsmanagement.dto.DeleteResponseREC;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.exception.ProblemProcessingDataException;
import com.hubproductsmanagement.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return saveProductInDatabase(productDTO, false);
    }

    @Transactional
    private ProductDTO saveProductInDatabase(ProductDTO productDTO, boolean isUpdate) {
        log.info("productEntity to be saved is :{}", productDTO);

        ProductEntity productEntity = null;
        ProductDTO savedProductDTO = null;

        try {
            if (isUpdate && productDTO.getId() == null)
                throw new ProblemProcessingDataException("Product id not provided!", null);
            Long productId = productDTO.getId();
            if (productId != null) {
                Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
                if (productEntityOptional.isEmpty()) {
                    throw new ProblemProcessingDataException("Product id not found!", null);
                }

            }

            productEntity = mapper.map(productDTO, ProductEntity.class);

            ProductEntity savedProduct = productRepository.save(productEntity);

            savedProductDTO = mapper.map(savedProduct, ProductDTO.class);

            log.info("productEntity saved is :{}", savedProductDTO);

            return savedProductDTO;

        } catch (Exception e) {
            throw new ProblemProcessingDataException("An error has occurred while saving product data!", e);
        }

    }


    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = null;

        try {
            Optional<ProductEntity> productEntity = productRepository.findById(id);

            if (productEntity.isPresent()) {
                productDTO = mapper.map(productEntity.get(), ProductDTO.class);
            }
        } catch (
                MappingException e) {
            throw new MappingException(e);
        } catch (RuntimeException re) {
            throw new ProblemProcessingDataException("Error while getting data from DB!",re);
        }

        return productDTO;


    }


    public ProductDTO updateProduct(ProductDTO productDto) {
        return saveProductInDatabase(productDto, true);
    }

    public DeleteResponseREC deleteProduct(Long id) {
        if (!productRepository.existsById(id)) throw new ProblemProcessingDataException("Operation impossible!", null);
        productRepository.deleteById(id);
        log.info("Product deleted: {} ", id);
        return new DeleteResponseREC("Product with id: " + id + " successfully deleted!");
    }

}
