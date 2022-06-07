package com.company.team.data.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * trả về thông tin mặc định của user
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDto implements Serializable {

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;


}
