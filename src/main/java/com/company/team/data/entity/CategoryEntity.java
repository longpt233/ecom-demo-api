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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_tb")
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_name", length = 45)
    // FIXME: Illegal mix of collations (latin1_swedish_ci,IMPLICIT) and (utf8mb4_general_ci,COERCIBLE) for operation '='
    // ALTER TABLE ecom.category_tb CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
    private String name;

    @Column(name = "category_description", length = 45)
    private String description;

    @Column(name = "category_created_at", updatable = false)
    private Date createdDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)  // mappedBy trùng với field bên Product
    private List<ProductEntity> productEntities = new ArrayList<>();

}
