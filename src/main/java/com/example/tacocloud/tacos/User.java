package com.example.tacocloud.tacos;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private String name;
    private Set<String> checkedOptions;


}
