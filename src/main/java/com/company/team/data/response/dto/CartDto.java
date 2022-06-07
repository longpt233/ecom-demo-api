package com.company.team.data.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartDto {


    private Double totalPrice;
    private List<CartProductDto> cartProductVMS;

}
