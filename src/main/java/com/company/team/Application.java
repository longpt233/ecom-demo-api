package com.company.team;

import com.company.team.service.implement.FilesStorageService;
import com.company.team.service.implement.InitDbService;
import com.company.team.service.implement.RoleService;
import com.company.team.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Resource
    InitDbService initDbService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
    @Override
    public void run(String... args) {
//        initDbService.init();
    }
}
