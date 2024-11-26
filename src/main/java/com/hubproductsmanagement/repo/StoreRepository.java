package com.hubproductsmanagement.repo;

import com.hubproductsmanagement.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}