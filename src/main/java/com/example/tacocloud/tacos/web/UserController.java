package com.example.tacocloud.tacos.web;

import com.example.tacocloud.tacos.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/form")
public class UserController {

    @GetMapping
    public String showForm(Model model) {
        System.out.println("1");
        User user = new User();
        Set<String> options = new HashSet<>(Arrays.asList("Option 1", "Option 2", "Option 3"));
        model.addAttribute("user", user);
        model.addAttribute("options", options);
        System.out.println(model);
        return "testpost";
    }

    @PostMapping
    public String submitForm(@ModelAttribute User user) {
        System.out.println(user);
        // 处理表单提交
        return "redirect:/success";
    }

}