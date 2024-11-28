package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.dto.ProductRequestDTO;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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

        ProductRequestDTO productDTO = generateProductDto();
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);

        when(productRepositoryMock.save(any())).thenReturn(productEntity);
        when(productRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(productEntity));

        ProductDTO savedProduct = productService.createProduct(productDTO);

        assertEquals(savedProduct.getProductName(),productDTO.getProductName());
        assertEquals(savedProduct.getSupplier(),productDTO.getSupplier());

    }

    ProductRequestDTO generateProductDto() {

        ProductRequestDTO productDTO = new ProductRequestDTO();
        productDTO.setId(1L);
        productDTO.setProductName("some name");
        productDTO.setDescription("some desc");
        productDTO.setSupplier("some supplier");

        return productDTO;

    }



}
