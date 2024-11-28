package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.StoreDTO;
import com.hubproductsmanagement.dto.DeleteResponseREC;
import com.hubproductsmanagement.dto.StoreRequestDTO;
import com.hubproductsmanagement.repo.StoreRepository;
import com.hubproductsmanagement.service.StoreService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("hub-products-management/stores")
public class StoreController {
    private StoreService storeService;

    private StoreRepository storeRepository;
    private Mapper mapper;

    public StoreController(StoreService storeService, StoreRepository storeRepository) {
        this.storeService = storeService;
        this.storeRepository = storeRepository;
        this.mapper= DozerBeanMapperBuilder.buildDefault();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public StoreDTO createStore(@RequestBody @Valid StoreRequestDTO storeDto, @RequestHeader HttpHeaders headers) {
        log.info("Request to create product with params: {} ", storeDto);
        return storeService.createStore(storeDto);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public StoreDTO updateStore(@RequestBody @Valid StoreRequestDTO storeDto, @RequestHeader HttpHeaders headers) {
        log.info("Request to update store with params: {} ", storeDto);
        return storeService.updateStore(storeDto);
    }

    @GetMapping("/{id}")
    public StoreDTO getStore(@PathVariable Long id) {
        log.info("Request to select store with id: {} ", id);
        return storeService.getStore(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public DeleteResponseREC deleteStore(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        log.info("Request to delete store with id: {} ", id);
        return storeService.deleteStore(id);
    }

    @GetMapping("/all/{ids}")
    public List<StoreDTO> getStoresByIds(@PathVariable List<Long> ids) {
        log.info("Request to select stores with ids: {} ", ids);
        return storeRepository.findAllById(ids)
                .stream()
                .map(storeEntity -> mapper.map(storeEntity, StoreDTO.class))
                .toList();
    }


}
