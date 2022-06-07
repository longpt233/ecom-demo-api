package com.company.team.service.implement;

import com.company.team.data.request.SignupRequest;
import com.company.team.enums.RoleEnum;
import com.company.team.repository.RoleRepository;
import com.company.team.repository.UserRepository;
import com.company.team.security.model.Role;
import com.company.team.security.model.User;
import com.company.team.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Override phương thức trong class UserDetailsService
     *
     * @param username
     * @return UserDetailsImpl là implements của UserDetails (UserDetails là đối tượng Spring security sử dụng để authen và authorize)
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Transactional
    public User loadUserInfoByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return (user);
    }
}