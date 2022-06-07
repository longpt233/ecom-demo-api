package com.company.team.controller.auth;

import com.company.team.data.request.LoginRequest;
import com.company.team.data.request.SignupRequest;
import com.company.team.data.request.TokenRefreshRequest;
import com.company.team.data.response.base.MyResponse;
import com.company.team.enums.RoleEnum;
import com.company.team.exception.custom.DuplicateRecordException;
import com.company.team.exception.custom.TokenRefreshException;
import com.company.team.repository.RoleRepository;
import com.company.team.repository.UserRepository;
import com.company.team.security.model.RefreshToken;
import com.company.team.security.model.Role;
import com.company.team.security.model.User;
import com.company.team.security.model.UserDetailsImpl;
import com.company.team.service.implement.RefreshTokenService;
import com.company.team.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            throw new DuplicateRecordException("Error: Username incorrect !");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        refreshTokenService.deleteByUserId(userDetails.getId());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        Map<String, Object> res = new HashMap<>();
        res.put("username", userDetails.getUsername());
        res.put("role", roles);
        res.put("accessToken", jwt);
        res.put("refreshToken", refreshToken.getToken());
        res.put("refreshTokenExpiredIn", refreshToken.getExpiryDate());

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("nhan thanh cong")
                .buildData(res)
                .get();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new DuplicateRecordException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicateRecordException("Error: Email is already in use!");
        }

        Set<String> asignRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Nếu không truyền thì set role mặc định là ROLE_USER
        if (asignRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.USER.getRole())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            asignRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN.getRole())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.USER.getRole())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }


        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        user.setRoles(roles);
        userRepository.save(user);

        MyResponse response = MyResponse
                .builder()
                .buildCode(200)
                .buildMessage("nhan thanh cong")
                .get();
        return ResponseEntity.ok(response);

    }


    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {

                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    refreshTokenService.deleteByUserId(user.getId());
                    RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

                    Map<String, Object> res = new HashMap<>();
                    res.put("token", token);
                    res.put("refreshToken", refreshToken.getToken());
                    res.put("expiredIn", refreshToken.getExpiryDate());

                    MyResponse response = MyResponse
                            .builder()
                            .buildCode(200)
                            .buildMessage("thanh cong")
                            .buildData(res)
                            .get();
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
