package com.hubproductsmanagement.entity;

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
 
    private String name;

    private String supplier;

    private String description;


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductStoreEntity> storeProductList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(supplier, that.supplier) && Objects.equals(description, that.description)  && Objects.equals(storeProductList, that.storeProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, supplier, description, storeProductList);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}