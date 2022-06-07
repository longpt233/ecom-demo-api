package com.company.team.service.implement;

import com.company.team.data.mapper.CategoryMapper;
import com.company.team.data.entity.CategoryEntity;
import com.company.team.data.request.CategoryRequest;
import com.company.team.data.response.dto.CategoryDto;
import com.company.team.exception.custom.DuplicateRecordException;
import com.company.team.exception.custom.InternalServerException;
import com.company.team.exception.custom.NotFoundException;
import com.company.team.repository.CategoryRepository;
import com.company.team.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getListCategory() {

        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toCategoryModel).collect(Collectors.toList());

    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new NotFoundException("No category found");
        }
        return CategoryMapper.toCategoryModel(category.get());
    }

    @Override
    public CategoryDto createCategory(CategoryRequest category) {

        // check xem co ten chua
        CategoryEntity categoryEntity = categoryRepository.findAllByName(category.getName());
        if (categoryEntity != null) {
            throw new DuplicateRecordException("Ten danh muc da ton tai");
        }

        categoryEntity = CategoryMapper.toCategoryEntity(category);
        CategoryEntity returnCreate = categoryRepository.saveAndFlush(categoryEntity);
        return CategoryMapper.toCategoryModel(returnCreate);
    }

    @Override
    public CategoryDto updateCategory(CategoryRequest categoryRequest, long categoryId) {

        // check exists
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new NotFoundException("No category found");
        }

        // update
        CategoryEntity updateCategory = CategoryMapper.toCategoryEntity(categoryRequest, categoryId);
        try {
            categoryRepository.saveAndFlush(updateCategory);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update category");
        }
        return CategoryMapper.toCategoryModel(category.get());
    }

    @Override
    public void deleteCategory(long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new NotFoundException("No category found");
        }
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete category");
        }
    }


}
