package com.example.tacocloud.tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.sql.Date;
import java.util.List;
@Data
public class Taco {
    private Long id;
    private Date createdAt;
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1,message="You must choose at least 1 ingredient")
    private List<String> ingredients;
}
