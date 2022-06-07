package com.company.team.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "cart_tb")
public class CartEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private long id;

    /**
     * FIXME: cái này map với bảng user tuy nhiên k add khóa cho đỡ mệt
     */
    @Column(name = "cart_username")
    private String username;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartEntity", fetch = FetchType.LAZY)  // cascade-> xoa cart -> xoa het cart item nhe
    private List<CartItemEntity> listCartItemEntities = new ArrayList<>();

    @Column(name = "cart_updated_at")
    private Date updateDate;

    @Column(name = "cart_created_at", updatable = false)
    private Date createdDate;

    public CartEntity(){
        createdDate = new Date();
    }

}
