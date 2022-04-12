package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleService ; //  doing injection
    UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){


        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.findAll())    ;//  bring me all roles from DB
        model.addAttribute("users",userService.findAll());

        return "user/create";
    }
    @PostMapping("/create")
    public String insertUser (@ModelAttribute("user") UserDTO user ,Model model) { //modelattribute to hold data what we fill in

       // model.addAttribute("user",new UserDTO()); //  trying to show & populate empty form
      //  model.addAttribute("roles",roleService.findAll()) ;
        userService.save(user); // save the data to db before showing it
      //  model.addAttribute("users",userService.findAll());

        return "redirect:/user/create";
    }
}
