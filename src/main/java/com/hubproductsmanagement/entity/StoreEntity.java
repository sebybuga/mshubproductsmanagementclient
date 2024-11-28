package com.hubproductsmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "createdBy", updatable = false)
    private String createdBy;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

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
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(storeName, that.storeName) && Objects.equals(zipCode, that.zipCode) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(storeProductList, that.storeProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, zipCode, address, city, country, createdBy, createdAt, updatedBy, updatedAt, storeProductList);
    }

    @Override
    public String toString() {
        return "StoreEntity{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", storeProductList=" + storeProductList +
                '}';
    }
}