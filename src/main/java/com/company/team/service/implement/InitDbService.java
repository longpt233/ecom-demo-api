package com.company.team.service.implement;

import com.company.team.data.entity.CategoryEntity;
import com.company.team.data.entity.ProductEntity;
import com.company.team.enums.RoleEnum;
import com.company.team.repository.CategoryRepository;
import com.company.team.repository.ProductRepository;
import com.company.team.repository.RoleRepository;
import com.company.team.repository.UserRepository;
import com.company.team.security.model.Role;
import com.company.team.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitDbService {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    private RoleService roleService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public void init(){

        //        storageService.deleteAll();
        storageService.init();

        roleService.init();

        User admin = new User("longpt","longpt@gmail.com", encoder.encode("admin123"));
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN.getRole())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        admin.setRoles(roles);
        userRepository.save(admin);

        User user = new User("user1","user1@gmail.com", encoder.encode("user1"));
        Set<Role> rolesUser = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleEnum.USER.getRole())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        rolesUser.add(userRole);
        user.setRoles(rolesUser);
        userRepository.save(user);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setDescription("mo ta 1");
        categoryEntity.setName("quan ao nam");
        categoryRepository.saveAndFlush(categoryEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(categoryEntity);
        productEntity.setAvailable(10);
        productEntity.setName("quan bo");
        productEntity.setPrice(1000000d);
        productEntity.setImageUrl("[\"url 1\",\"url 2\"]");
        productRepository.saveAndFlush(productEntity);
    }
}
