package com.company.team.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_tb")
public class OrderEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "price")
    private double price;

    @Column (name = "created_date")
    private Date createdDate;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntityMap")
    private List<OrderItemEntity> listProductOrders = new ArrayList<>();

}
