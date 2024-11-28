package com.hubproductsmanagement.repo;

import com.hubproductsmanagement.entity.ProductStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductStoreRepository extends JpaRepository<ProductStoreEntity, Long> {

}