package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.*;
import com.hubproductsmanagement.entity.ProductStoreEntity;
import com.hubproductsmanagement.entity.StoreEntity;
import com.hubproductsmanagement.exception.ProblemProcessingDataException;
import com.hubproductsmanagement.repo.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StoreService {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private StoreRepository storeRepository;


    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDTO createStore(StoreRequestDTO storeDTO) {

        return saveStoreInDatabase(storeDTO, false);

    }

    @Transactional
    private StoreDTO saveStoreInDatabase(StoreRequestDTO storeRequestDTO, boolean isUpdate) {
        log.info("storeEntity to be saved is :{}", storeRequestDTO);

        StoreEntity storeToBeSavedEntity = null;
        StoreDTO savedStoreDTO = null;

        try {
            if (isUpdate) {
                if (storeRequestDTO.getId() == null) throw new ProblemProcessingDataException("Store id not provided!",null);

                Long storeId = storeRequestDTO.getId();

                Optional<StoreEntity> storeEntityOptional = storeRepository.findById(storeId);
                if (storeEntityOptional.isEmpty()) {
                    throw new ProblemProcessingDataException("Store not found!",null);
                }
            }
            storeToBeSavedEntity = mapper.map(storeRequestDTO, StoreEntity.class);

            List<ProductStoreRequestDTO> storeProductRequestDTOList = storeRequestDTO.getStoreProductList();

            if (storeProductRequestDTOList != null && !storeProductRequestDTOList.isEmpty()) {
                setProductStoreList(storeToBeSavedEntity, storeProductRequestDTOList);
            }

            StoreEntity savedStore = storeRepository.save(storeToBeSavedEntity);

            savedStoreDTO = mapper.map(savedStore, StoreDTO.class);

        } catch (Exception e) {
            throw new ProblemProcessingDataException("An error has occurred while saving store data!",e);
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
        return saveStoreInDatabase(storeDto, true);
    }


    private void setProductStoreList(StoreEntity storeToBeSavedEntity, List<ProductStoreRequestDTO> storeProductRequestDTOList) {
        List<ProductStoreEntity> storeProductEntityList;
        storeToBeSavedEntity.getStoreProductList().clear();

        storeProductEntityList = storeProductRequestDTOList.stream()
                .map(storeProductRequest -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(storeProductRequest.getProductId());
                    storeProductRequest.setProduct(productDTO);

                    ProductStoreEntity productStoreEntity = mapper.map(storeProductRequest, ProductStoreEntity.class);
                    productStoreEntity.setStore(storeToBeSavedEntity);
                    return productStoreEntity;
                })
                .toList();

        storeToBeSavedEntity.setStoreProductList(storeProductEntityList);

    }

    @Transactional
    public StoreDeleteResponseDTO deleteStore(Long id) {
        if (!storeRepository.existsById(id)) throw new ProblemProcessingDataException("Operation impossible!", null);
        storeRepository.deleteById(id);
        log.info("Store deleted: {} ", id);
        return new StoreDeleteResponseDTO("Store with id " + id+ " successfully deleted!");

    }


}
