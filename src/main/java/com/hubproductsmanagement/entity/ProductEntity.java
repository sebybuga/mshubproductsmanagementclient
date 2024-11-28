package com.hubproductsmanagement.entity;

import com.hubproductsmanagement.constant.ProductStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "products")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String productName;

    private String supplier;

    private String description;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatusEnum status;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private transient List<ProductStoreEntity> storeProductList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(supplier, that.supplier) && Objects.equals(description, that.description)  && Objects.equals(storeProductList, that.storeProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, supplier, description, storeProductList);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}