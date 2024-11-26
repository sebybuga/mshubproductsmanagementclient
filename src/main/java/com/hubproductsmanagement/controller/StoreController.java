package com.hubproductsmanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.StoreDTO;
import com.hubproductsmanagement.dto.StoreRequestDTO;
import com.hubproductsmanagement.repo.StoreRepository;
import com.hubproductsmanagement.service.AuthenticationService;
import com.hubproductsmanagement.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("hub-products-management/stores")
public class StoreController {
	private StoreService storeService;

	private AuthenticationService authService;
	private StoreRepository storeRepository;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public StoreController(StoreService storeService, StoreRepository storeRepository, AuthenticationService authService) {
		this.storeService = storeService;
		this.storeRepository=storeRepository;
		this.authService = authService;
	}

	@PostMapping
	public ResponseEntity<StoreDTO> createStore(@RequestBody @Valid StoreRequestDTO storeDto, @RequestHeader HttpHeaders headers) {
		try {
			if (!authService.authenticate(headers)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

				return new ResponseEntity<>(storeService.createStore(storeDto),HttpStatus.OK);

		} catch (Exception e) {
			log.info("An error has occurred while updating product with params: {}, error:{}  "
					,storeDto, e);
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@PutMapping
	public ResponseEntity<StoreDTO> updateStore(@RequestBody @Valid StoreRequestDTO storeDto, @RequestHeader HttpHeaders headers) {
			try {
				if (!authService.authenticate(headers)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
				if (storeDto.getId() == null) throw new Exception("Store id not provided!");
				return new ResponseEntity<>(storeService.updateStore(storeDto),HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}

	@GetMapping("/{id}")
	public StoreDTO getStore(@PathVariable Long id) {
		return storeService.getStore(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteStore(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
		if (!authService.authenticate(headers)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		storeService.deleteStore(id);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	
	@GetMapping("/all/{ids}")
	public List<StoreDTO> getStoresByIds(@PathVariable List<Long> ids){

		return storeRepository.findAllById(ids)
				.stream()				
                .map(storeEntity -> mapper.map(storeEntity, StoreDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
