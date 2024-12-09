package com.facul.cefet_store.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;

    private String password;

}
