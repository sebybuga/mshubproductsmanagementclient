package com.hubproductsmanagement.repo;

import com.hubproductsmanagement.entity.ProductStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


@Repository
public interface ProductStoreRepository extends JpaRepository<ProductStoreEntity, Long> {

    @Transactional
    void deleteByStoreId(Long storeId);

}