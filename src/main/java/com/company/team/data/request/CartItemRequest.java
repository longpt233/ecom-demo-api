package com.company.team.data.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CartItemRequest {

    long productId;
    int amount;

}
