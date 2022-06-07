package com.company.team.controller.admin;


import com.company.team.data.response.base.MyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api")
public class OrderManagerController {


    @GetMapping("/admin/auth/v1/order-history")
    public ResponseEntity<?> orderHistory() {
        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("not implement")
                .get();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/auth/v1/order-history/{orderId}")
    public ResponseEntity<?> getOrderDetail() {
        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("not implement")
                .get();

        return ResponseEntity.ok(response);
    }
}
