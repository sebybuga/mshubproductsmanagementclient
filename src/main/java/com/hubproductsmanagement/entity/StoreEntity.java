package com.hubproductsmanagement.entity;

import com.hubproductsmanagement.constant.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Table(name = "stores")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime storeDate;

    @Column(name = "createdBy", updatable = false)
    private String createdBy;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.ORDINAL)
    private ProductStatusEnum storeStatusId;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "store"
    )
    private List<ProductStoreEntity> storeProductList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreEntity)) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(storeDate, that.storeDate) && Objects.equals(createdBy, that.createdBy)
                && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedAt, that.updatedAt)
                && storeStatusId == that.storeStatusId && Objects.equals(storeProductList, that.storeProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeDate, createdBy, createdAt, updatedBy, updatedAt, storeStatusId, storeProductList);
    }

    @Override
    public String toString() {
        return "StoreEntity{" +
                "id=" + id +
                ", storeDate=" + storeDate +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", storeStatusId=" + storeStatusId +
                '}';
    }
}