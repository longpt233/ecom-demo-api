package com.company.team.controller.user;

import com.company.team.data.entity.*;
import com.company.team.data.request.OrderRequest;
import com.company.team.data.response.base.MyResponse;
import com.company.team.data.response.dto.OrderInfoDto;
import com.company.team.data.response.dto.OrderUserHistoryDto;
import com.company.team.security.model.User;
import com.company.team.service.implement.OrderService;
import com.company.team.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;




    /**
     * tạo một giỏi hàng (chưa lưu db) với thông tin mặc định, trả về để hiên thị thông tin hiện tại.
     * @return
     */
    @GetMapping("/user/auth/v1/checkout")
    public ResponseEntity<?> getInfocheckoutDefault() {

        OrderInfoDto order = new OrderInfoDto();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userCustom = userService.loadUserInfoByUsername(username);

        order.setAddress(userCustom.getAddress());
        order.setCustomerName(userCustom.getUsername());
        order.setPhoneNumber(userCustom.getPhoneNumber());
        order.setEmail(userCustom.getEmail());

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .buildData(order)
                .get();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/auth/v1/checkout")
    public ResponseEntity<?> checkout(@Valid @RequestBody OrderRequest orderReq) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderEntity order = orderService.createOrder(orderReq,username);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .buildData(order)
                .get();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/auth/v1/order-history")
    public ResponseEntity<?> orderHistory() {

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderUserHistoryDto orderUserHistoryDto = orderService.getUserHistory(username);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("thành công")
                .buildData(orderUserHistoryDto)
                .get();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/user/auth/v1/order-history/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("orderId") int orderId) {


        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("method not implement, orderId = " + orderId)
                .get();

        return ResponseEntity.ok(response);
    }
}
