package com.hubproductsmanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hubproductsmanagement.dto.DeleteResponseREC;
import com.hubproductsmanagement.dto.ProductDTO;
import com.hubproductsmanagement.dto.ProductRequestDTO;
import com.hubproductsmanagement.entity.ProductEntity;
import com.hubproductsmanagement.exception.ProblemProcessingDataException;
import com.hubproductsmanagement.repo.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepositoryMock;

    private ProductRequestDTO productDTO;
    private ProductEntity productEntity;

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    @Before
    public void setUp() {
        productDTO = generateProductDto();
        productEntity = mapper.map(productDTO, ProductEntity.class);

        when(productRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(productEntity));
    }

    @Test
    public void testCreateProduct() {
        when(productRepositoryMock.save(any())).thenReturn(productEntity);

        ProductDTO savedProduct = productService.createProduct(productDTO);

        assertEquals(savedProduct.getProductName(),productDTO.getProductName());
        assertEquals(savedProduct.getSupplier(),productDTO.getSupplier());

    }

    @Test
    public void testUpdateProduct() {
        when(productRepositoryMock.save(any())).thenReturn(productEntity);

        ProductDTO savedProduct = productService.updateProduct(productDTO);

        assertEquals(savedProduct.getProductName(),productDTO.getProductName());
        assertEquals(savedProduct.getSupplier(),productDTO.getSupplier());

    }
    @Test
    public void testGetProduct(){
        ProductDTO savedProduct = productService.getProduct(productDTO.getId());

        assertEquals(savedProduct.getProductName(),productDTO.getProductName());
        assertEquals(savedProduct.getSupplier(),productDTO.getSupplier());

    }

    @Test
    public void testDeleteProduct_success(){
        when(productRepositoryMock.existsById(anyLong())).thenReturn(true);
        DeleteResponseREC response = productService.deleteProduct(productDTO.getId());
        String idToSearch = productDTO.getId().toString();
        assertTrue(response.message().contains(idToSearch));

    }

    @Test
    public void testDeleteProduct_fail(){
        when(productRepositoryMock.existsById(anyLong())).thenReturn(false);
        assertThrows(ProblemProcessingDataException.class, () ->
                productService.deleteProduct(productDTO.getId()));

    }


    ProductRequestDTO generateProductDto() {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setId(1L);
        productRequestDTO.setProductName("some name");
        productRequestDTO.setDescription("some desc");
        productRequestDTO.setSupplier("some supplier");

        return productRequestDTO;
    }



}
