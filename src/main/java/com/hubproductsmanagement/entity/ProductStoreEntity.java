package com.hubproductsmanagement.entity;

import com.hubproductsmanagement.constant.CurrencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStoreEntity)) return false;
        ProductStoreEntity that = (ProductStoreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(store, that.store) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) &&  Objects.equals(currency , that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, product, quantity, price, currency);
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
                '}';
    }
}
