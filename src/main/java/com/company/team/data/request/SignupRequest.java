package com.company.team.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SignupRequest {

    String username;
    String email;
    String password;
    Set<String> role;


}
