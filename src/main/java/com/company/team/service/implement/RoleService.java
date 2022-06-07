package com.company.team.service.implement;

import com.company.team.enums.RoleEnum;
import com.company.team.repository.RoleRepository;
import com.company.team.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void init() {
        Role roleInit = new Role();
        roleInit.setId(112233);
        roleInit.setName(RoleEnum.ADMIN.getRole());
        roleRepository.save(roleInit);

        Role roleUser = new Role();
        roleUser.setId(112255);
        roleUser.setName(RoleEnum.USER.getRole());
        roleRepository.save(roleUser);
    }

}
