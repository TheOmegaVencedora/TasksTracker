package com.todowebsite.sample.demo.znotUsed.service;

import com.todowebsite.sample.demo.dao.UserDao;
import com.todowebsite.sample.demo.entity.Tasks;
import com.todowebsite.sample.demo.entity.Users;
import com.todowebsite.sample.demo.user.CrmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
public interface UserService extends UserDetailsService {

    void  save(CrmUser crmUser);



}

package com.todowebsite.sample.demo.controller;

        import com.todowebsite.sample.demo.dao.UserDao;
        import com.todowebsite.sample.demo.entity.Tasks;
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


    private static final Logger logger = LoggerFactory.getLogger(com.todowebsite.sample.demo.controller.MainController.class);



    @Autowired
    public UserDao userDao;







    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }


    @CrossOrigin
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




/**

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

        return "HomePage";
    }


    @GetMapping("/add")
    public String addTask(Model model){

        Tasks tasks = new Tasks();

        model.addAttribute("tasks", tasks);

        return "addPage";
    }

    @PostMapping("/addTasks")
    public String adding(@ModelAttribute("tasks")Tasks theTasks, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "rough";
        }

        userDao.addTasks(theTasks);

        return "redirect:/home";
    }




}



**/


