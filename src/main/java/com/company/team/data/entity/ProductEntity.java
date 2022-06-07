package com.company.team.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * entity = table in database
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "product_tb")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @JsonBackReference
    @OneToOne(mappedBy = "productEntityMap")
    private CartItemEntity cartItemEntity;

    // FIXME: không add quan hệ ngược lại nữa
    // nếu một product bị xóa thì đến khi get lại đơn hàng thì sẽ có lỗi là chắc
//    @JsonBackReference
//    @OneToOne(mappedBy = "productOrderEntityMap", cascade = CascadeType.ALL, orphanRemoval = true)
//    private OrderItemEntity orderItemEntity;

    @NotNull(message = "Product name is required.")
    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "product_desc")
    private String description;

    @Column(name = "product_images")
    private String imageUrl;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private CategoryEntity category;

    @Column(name = "product_available")
    private int available;

    @Column(name = "product_rating")
    private int rating;

    @Column(name = "product_color")
    private String color;
    @Column(name = "product_brand")
    private String brand;

    @Column(name = "product_sold")
    private int sold;

    @Column(name = "product_updated_at")
    private Date updateDate;

    @Column(name = "product_created_at", updatable = false)
    private Date createdDate;

}
