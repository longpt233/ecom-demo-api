package com.company.team.data.request;

import com.company.team.data.entity.CategoryEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ProductRequest {

    long categoryId;
    String name;
    String description;
    Double price;
    int available;
    Set<String> imageUrl;
    Set<String> color;
    String brand;
    int sold;
    int rating;
}
