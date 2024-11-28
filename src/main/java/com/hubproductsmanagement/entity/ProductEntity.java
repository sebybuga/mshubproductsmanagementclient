package com.hubproductsmanagement.entity;

import com.hubproductsmanagement.constant.ProductStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Column(name = "createdBy", updatable = false)
    private String createdBy;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private transient List<ProductStoreEntity> storeProductList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(productName, that.productName) && Objects.equals(supplier, that.supplier) && Objects.equals(description, that.description) && status == that.status && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(storeProductList, that.storeProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, supplier, description, status, createdBy, createdAt, updatedBy, updatedAt, storeProductList);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", storeProductList=" + storeProductList +
                '}';
    }
}