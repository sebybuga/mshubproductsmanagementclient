package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepositoryMock;

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();


    @Test
    public void testCreateProduct() {

        ProductDTO productDTO = generateProductDto();
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
        HttpHeaders headers = new HttpHeaders();
        when(productRepositoryMock.save(any())).thenReturn(productEntity);
        when(productRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(productEntity));

        ResponseEntity<ProductDTO> savedProduct = productService.createProduct(productDTO, headers);

        assertEquals(savedProduct.getBody().getName(),productDTO.getName());
        assertEquals(savedProduct.getBody().getSupplier(),productDTO.getSupplier());

    }

    ProductDTO generateProductDto() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("some name");
        productDTO.setDescription("some desc");
        productDTO.setSupplier("some supplier");

        return productDTO;

    }



}
