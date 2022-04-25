package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleService ; //  doing injection for accessing the class
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

        return "user/create"; //  what view you want to see
    }
    @PostMapping("/create")
    public String insertUser (@ModelAttribute("user") UserDTO user ,Model model) { //model attribute to hold data what we fill in

       // model.addAttribute("user",new UserDTO()); //  trying to show & populate empty form
      //  model.addAttribute("roles",roleService.findAll()) ;
        userService.save(user); // save the data to db before showing it
      //  model.addAttribute("users",userService.findAll());

        return "redirect:/user/create";
    }

   //  this is get user info to the form
    @GetMapping("/update/{username}") // username paramaters helps define specific user
    public String editUser(@PathVariable("username") String username, Model model){
         //use pathVariable to bring specific userName to the method

        //what attribute we are looking for,  go back to HTML and look at  ${""}

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles",roleService.findAll()) ;
        model.addAttribute("users",userService.findAll());

        return "/user/update";  //  what view you want to see
    }

    //  this is for save button
    @PostMapping ("/update")
    public String updateUser(UserDTO user){

      //  we attribut we can add here. go to HTML to look at ${}
         userService.update(user);

        return "redirect:/user/create"; //using redirect you don't need to write model.attribute again.
    }


    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create" ;
    }


}
