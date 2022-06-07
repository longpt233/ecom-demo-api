package com.company.team.dev;


import com.company.team.data.response.base.MyResponse;

public class Test {
    public static void main(String[] args) {
        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("nhan thanh cong")
                .get();

        System.out.println(response.toString());
    }
}
