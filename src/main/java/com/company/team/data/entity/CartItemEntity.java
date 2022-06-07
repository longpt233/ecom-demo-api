package com.company.team.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "cart_item_tb")
public class CartItemEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;


    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)  // cascade-> xoa cart -> xoa het entity mat
    @JoinColumn(name = "item_product_id", referencedColumnName = "product_id")
    private ProductEntity productEntityMap ;

    @Column(name = "item_amount")
    private int amount;

    public CartItemEntity(CartEntity cartEntity, ProductEntity product, int amount) {
        this.cartEntity = cartEntity;
        this.productEntityMap = product;
        this.amount = amount;
    }
}
