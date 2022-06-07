package com.company.team.data.response.dto;


import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * final model view return to client
 */

@Data
public class ProductDto {

    long productId;
    String name;
    Double price;
    String description;
    Set<String> imageUrls;
    Set<String> colors;
    String category;
    int sold;
    int rating;
}
