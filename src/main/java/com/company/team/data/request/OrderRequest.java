package com.company.team.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private String customerName;
    private String phoneNumber;
    private String address;
    private String email;

}
