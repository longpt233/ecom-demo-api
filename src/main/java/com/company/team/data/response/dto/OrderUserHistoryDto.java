package com.company.team.data.response.dto;

import com.company.team.data.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserHistoryDto {
    private String username;
    private List<OrderEntity> orderList;
}
