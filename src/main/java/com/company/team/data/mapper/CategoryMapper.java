package com.company.team.data.mapper;

import com.company.team.data.entity.CategoryEntity;
import com.company.team.data.request.CategoryRequest;
import com.company.team.data.response.dto.CategoryDto;

import java.util.Date;

public class CategoryMapper {

    public static CategoryDto toCategoryModel(CategoryEntity category) {

        CategoryDto categoryViewModel = new CategoryDto();

        categoryViewModel.setCategoryId(category.getCategoryId());
        categoryViewModel.setName(category.getName());
        categoryViewModel.setDescription(category.getDescription());
        categoryViewModel.setCreatedDate(new Date());

        return categoryViewModel;
    }

    public static CategoryEntity toCategoryEntity(CategoryRequest category, long categoryId) {

        CategoryEntity categoryEntity = toCategoryEntity(category);
        categoryEntity.setCategoryId(categoryId);

        return categoryEntity;
    }

    public static CategoryEntity toCategoryEntity(CategoryRequest category) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        return categoryEntity;
    }

}
