package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.StoreDTO;
import com.hubproductsmanagement.dto.StoreRequestDTO;
import com.hubproductsmanagement.repo.StoreRepository;
import com.hubproductsmanagement.service.StoreService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("hub-products-management/api/stores")
public class StoreController {
	private StoreService storeService;
	private StoreRepository storeRepository;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public StoreController(StoreService storeService, StoreRepository storeRepository) {
		this.storeService = storeService;
		this.storeRepository=storeRepository;
	}

	@PostMapping
	public StoreDTO createStore(@RequestBody @Valid StoreRequestDTO storeDto) {
		return storeService.createStore(storeDto);
	}
	
	@PutMapping
	public StoreDTO updateStore(@RequestBody @Valid StoreRequestDTO storeDto) {
			try {
				if (storeDto.getId() == null) throw new Exception("Store id not provided!");
				return storeService.updateStore(storeDto);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@GetMapping("/{id}")
	public StoreDTO getStore(@PathVariable Long id) {
		return storeService.getStore(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStore(@PathVariable Long id) {
		storeService.deleteStore(id);
	}
	
	@GetMapping("/all/{ids}")
	public List<StoreDTO> getStoresByIds(@PathVariable List<Long> ids){

		return storeRepository.findAllById(ids)
				.stream()				
                .map(storeEntity -> mapper.map(storeEntity, StoreDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
