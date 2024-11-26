package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.constant.ProductStatusEnum;
import com.hubproductsmanagement.dto.StoreDTO;
import com.hubproductsmanagement.dto.ProductStoreRequestDTO;
import com.hubproductsmanagement.dto.StoreRequestDTO;
import com.hubproductsmanagement.entity.StoreEntity;
import com.hubproductsmanagement.entity.ProductStoreEntity;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.repo.ProductStoreRepository;
import com.hubproductsmanagement.repo.StoreRepository;
import com.hubproductsmanagement.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StoreService {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private StoreRepository storeRepository;
    private ProductRepository productRepository;
    private ProductStoreRepository storeProductRepository;

    public StoreService(StoreRepository storeRepository, ProductRepository productRepository, ProductStoreRepository storeProductRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.storeProductRepository = storeProductRepository;
    }

    public StoreDTO createStore(StoreRequestDTO storeDTO) {

        return saveStoreInDatabase(storeDTO);

    }

    @Transactional
    private StoreDTO saveStoreInDatabase(StoreRequestDTO storeDTO) {
        log.info("storeEntity to be saved is :{}", storeDTO);

        StoreEntity storeEntity = null;
        StoreDTO savedStoreDTO = null;

        try {

            Long storeId = storeDTO.getId();
            if (storeId != null) {
                Optional<StoreEntity> storeEntityOptional = storeRepository.findById(storeId);
                if (storeEntityOptional.isEmpty()) {
                    throw new Exception("Store id not found!");
                }

            }

            storeEntity = mapper.map(storeDTO, StoreEntity.class);

            List<ProductStoreRequestDTO> storeProductRequestDTOList = storeDTO.getStoreProductList();

            if (storeProductRequestDTOList != null && !storeProductRequestDTOList.isEmpty()) {
                setProductStoreList(storeEntity, storeProductRequestDTOList);
            }

            StoreEntity savedStore = storeRepository.save(storeEntity);

            savedStoreDTO = mapper.map(savedStore, StoreDTO.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return savedStoreDTO;
    }


    public StoreDTO getStore(Long id) {
        StoreDTO storeDTO = null;
        Optional<StoreEntity> storeEntity = storeRepository.findById(id);

        if (storeEntity.isPresent()) {
            storeDTO = mapper.map(storeEntity.get(), StoreDTO.class);
        }

        return storeDTO;

    }


    public StoreDTO updateStore(StoreRequestDTO storeDto) {

        return saveStoreInDatabase(storeDto);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);

    }


    private void setProductStoreList(StoreEntity storeEntity, List<ProductStoreRequestDTO> storeProductRequestDTOList) {
        List<ProductStoreEntity> storeProductEntityList = new ArrayList<>();

        storeEntity.getStoreProductList().clear();

        Long storeId = storeEntity.getId();
        if (storeId!=null) {
            storeProductRepository.deleteByStoreId(storeId);
        }

        for (ProductStoreRequestDTO storeProductRequestDTO : storeProductRequestDTOList) {
            ProductStoreEntity storeProductEntity = new ProductStoreEntity();
            buildProductStoreEntity(
                    storeEntity,
                    storeProductEntity,
                    storeProductRequestDTO.getProductId(),
                    storeProductRequestDTO.getQuantity()

            );
            storeProductEntityList.add(storeProductEntity);

        }
        storeEntity.setStoreProductList(storeProductEntityList);

    }

    private void buildProductStoreEntity(StoreEntity storeEntity, ProductStoreEntity storeProductEntity, Long productId, Double quantity) {
        Optional<ProductEntity> productEntity;
        if (storeEntity != null) {
            storeEntity.setStoreProductList(null);
            storeProductEntity.setStore(storeEntity);
        }
        if (productId != null) {
            productEntity = productRepository.findById(productId);
            if (productEntity.isPresent()) {
                storeProductEntity.setProduct(productEntity.get());

            }
        }
        if (quantity != null) {
            storeProductEntity.setQuantity(quantity);
        }


    }


}
