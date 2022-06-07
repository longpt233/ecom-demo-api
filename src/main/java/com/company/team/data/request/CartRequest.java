package com.company.team.data.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
@Setter
@Getter
public class CartRequest {
    List<CartItemRequest> productList = new ArrayList<>();
}
