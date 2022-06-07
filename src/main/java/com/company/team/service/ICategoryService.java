package com.company.team.service;

import com.company.team.data.request.CategoryRequest;
import com.company.team.data.response.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getListCategory();

    CategoryDto getCategoryById(long categoryId);

    CategoryDto createCategory(CategoryRequest category);

    CategoryDto updateCategory(CategoryRequest category, long categoryId);

    void deleteCategory(long categoryId);

}
