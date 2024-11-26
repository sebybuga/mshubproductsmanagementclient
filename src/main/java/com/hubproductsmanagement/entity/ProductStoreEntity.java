package com.hubproductsmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.hubproductsmanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="storeId",referencedColumnName = "id")
    private StoreEntity store;

    @ManyToOne
    @JoinColumn(name="productId",referencedColumnName = "id")
    private ProductEntity product;

    private Double quantity;

    private Double price;


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "ro-RO", timezone = "Europe/Bucharest")
    private LocalDateTime refDate;

    @Enumerated(EnumType.ORDINAL)
    private CurrencyEnum currencyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStoreEntity)) return false;
        ProductStoreEntity that = (ProductStoreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(store, that.store) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && currencyId == that.currencyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, store, product, quantity, price, currencyId);
    }

    @Override
    public String toString() {
        return "ProductStoreEntity{" +
                "id=" + id +
                ", store=" + store +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", currencyId=" + currencyId +
                '}';
    }
}
