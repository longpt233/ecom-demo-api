package com.company.team.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role_tb")
@Getter
@Setter
public class Role {

    @Id
    private Integer id;

    @Column(length = 20)
    private String name;

    public Role() {

    }

}
