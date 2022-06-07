package com.company.team.controller.user;

import com.company.team.data.entity.CartEntity;
import com.company.team.data.request.CartRequest;
import com.company.team.data.response.base.MyResponse;
import com.company.team.service.implement.CartService;
import com.company.team.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * mot nguoi dung chi co 01 cart. gui product id voi amount len. check neu chua tao thi tao
 *
 */
@Controller
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;



    /**
     * tạo cart mới, update luôn nếu có
     * @return
     */
    @PostMapping("/user/auth/v1/carts")
    public ResponseEntity<?> cart(@Valid @RequestBody CartRequest cartRequest) {


        // FIXME: lấy tên rồi phải lấy cái id ra để query thì mới hợp lí được
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        cartService.createUpdateCart(cartRequest, username);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .get();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/user/auth/v1/carts")
    public ResponseEntity<?> getCart() {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CartEntity cartEntity= cartService.findByUsername(username);


        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .buildData(cartEntity)
                .get();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/user/auth/v1/carts")
    public ResponseEntity<?> deleteCartProduct() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CartEntity cartEntity= cartService.findByUsername(username);

        cartService.deleteCart(cartEntity.getId());

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .get();

        return ResponseEntity.ok(response);
    }

}
