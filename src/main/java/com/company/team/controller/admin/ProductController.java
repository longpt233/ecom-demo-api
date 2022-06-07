package com.company.team.controller.admin;

import com.company.team.data.request.ProductRequest;
import com.company.team.data.response.base.MyResponse;
import com.company.team.data.response.dto.ProductDto;
import com.company.team.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products")
    public ResponseEntity<MyResponse> getAllProduct(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {


        Map<String, Object> productList = productService.getAllProducts(page, size);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("nhan thanh cong")
                .buildData(productList)
                .get();

        return ResponseEntity.ok(response);


    }

    @PostMapping("/admin/auth/v1/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest categoryRequest) {

        ProductDto model = productService.createProduct(categoryRequest);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("Thêm mới thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {

        ProductDto model = productService.getProductBydId(id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/auth/v1/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable long id) {

        ProductDto model = productService.updateProduct(productRequest, id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("sửa thành công: " + model.getName())
                .buildData(model)
                .get();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/admin/auth/v1/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {

        productService.deleteProduct( id);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("xóa thành công:")
                .get();

        return ResponseEntity.ok(response);
    }
}
