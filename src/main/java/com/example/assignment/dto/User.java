package com.example.assignment.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private String user;
    private String pwd;
    private String token;


}
