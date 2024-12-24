package com.todowebsite.sample.demo.controller;

import com.todowebsite.sample.demo.dao.UserDao;
import com.todowebsite.sample.demo.entity.Users;
import com.todowebsite.sample.demo.znotUsed.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;




@Controller
public class MainController {


    AuthenticationService authenticationService;


    private static final Logger logger = LoggerFactory.getLogger(MainController.class);



    @Autowired
    public UserDao userDao;







    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }



    @GetMapping("/loginPage")
    public String loginPage() {


        return ("Login");

    }


    @GetMapping("/register")
    public String registration(Model theModel) {

        Users users = new Users();



        theModel.addAttribute("users", users);


        System.out.println("registration form check");

        System.out.println("users: " + users.getUsername() + " \n password: "+  users.getPassword());

        return ("Register");
    }


    /**
        @PostMapping("/authenticatingUser")
        public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model){

            System.out.println("login processing");

            Users theUsers = new Users();
            theUsers.setUsername(username);
            theUsers.setPassword(password);
            if(authenticationService.authenticate(theUsers)){
                model.addAttribute("username", username);
                return "/home";
            }else {
                //model.addAttribute("error", "invalid username or password");
                return "/Login";



            }



        }
     **/






    @PostMapping("/accepted")
    public String registered(@ModelAttribute("users")Users theUsers , BindingResult theBindingResult){

        logger.debug("User object: {}", theUsers);


        if (theBindingResult.hasErrors()){
            return "Register";
        }

        userDao.save(theUsers);

        System.out.println("entered registered");

        System.out.println("registration successful");
        


        return "redirect:/loginPage";

    }


    @GetMapping("/home")
    public  String mainPage(){

        return "TskMainReal";
    }


    @PostMapping("/add")
    public String addTask(){

        return "TskAdd";
    }




}


