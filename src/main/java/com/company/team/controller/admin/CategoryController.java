package com.company.team.controller.admin;

import com.company.team.data.request.CategoryRequest;
import com.company.team.data.response.base.MyResponse;
import com.company.team.data.response.dto.CategoryDto;
import com.company.team.service.implement.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/v1/categories")
    public ResponseEntity<?> getListCategory() {
        List<CategoryDto> category = categoryService.getListCategory();

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("categories", category);
        responseObj.put("totalItems", category.size());

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .buildData(responseObj)
                .get();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/auth/v1/categories")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        CategoryDto model = categoryService.createCategory(categoryRequest);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("Thêm mới thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/v1/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {

        CategoryDto model = categoryService.getCategoryById(id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/auth/v1/categories/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable long id) {

        CategoryDto model = categoryService.updateCategory(categoryRequest, id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("sửa thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/admin/auth/v1/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {

        categoryService.deleteCategory(id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("xóa thành công:")
                .get();

        return ResponseEntity.ok(response);
    }

}
