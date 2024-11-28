package com.hubproductsmanagement.entity;

import com.hubproductsmanagement.constant.CurrencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "product_stores")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId",referencedColumnName = "id")
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="productId",referencedColumnName = "id")
    private ProductEntity product;

    private Double quantity;

    private Double price;

    @Column(name = "currencyId")
    @Enumerated(EnumType.ORDINAL)
    private CurrencyEnum currency;

    @Column(name = "createdBy", updatable = false)
    private String createdBy;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStoreEntity that = (ProductStoreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(store, that.store) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && currency == that.currency
                && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, product, quantity, price, currency, createdBy, createdAt, updatedBy, updatedAt);
    }

    @Override
    public String toString() {
        return "ProductStoreEntity{" +
                "id=" + id +
                ", store=" + store +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", currency=" + currency +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
