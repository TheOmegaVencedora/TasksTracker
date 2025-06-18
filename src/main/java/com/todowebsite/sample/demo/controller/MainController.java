package com.todowebsite.sample.demo.Controller;

import com.todowebsite.sample.demo.Entity.Tasks;
import com.todowebsite.sample.demo.Entity.Users;
import com.todowebsite.sample.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;



    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }


    @GetMapping("/login")
    public String LoginPage(){

        return ("LoginPage");
    }


    @GetMapping("/register")
    public String registration(Model theModel){
        Users users = new Users();
        theModel.addAttribute("users", users);


        return "Register";
    }

    @PostMapping("/registering")
    public String registering(@ModelAttribute("users") Users users, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "register";
        }

       Users existing = userService.findByUserName(users.getUsername());

        if (existing != null){
            model.addAttribute("users", new Users());
            model.addAttribute("error", "username already exists");
        }

        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);



        userService.save(users);


        return "redirect:/login";
    }


    @GetMapping("/home")
    public String home(Model model, Principal principal){

        Users users = userService.findByUserName(principal.getName());
        List<Tasks> tasks = userService.getAllTasks(users.getId());
        model.addAttribute("tasks", tasks);

        System.out.println("Tasks: " + tasks);

        return "HomePage";
    }

    @GetMapping("/add")
    public String addTasks(Model model){
        Tasks tasks = new Tasks();

        model.addAttribute("tasks", tasks);

        return "addPage";
    }


    @PostMapping("/addTasks")
    public String processingTasks(@ModelAttribute Tasks tasks){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        Users users = userService.findByUserName(username);


        //UserDetails users1 = userService.loadUserByUsername(users.getUsername());

        System.out.println("name " + users.getUsername());



        tasks.setTitle(tasks.getTitle());
        tasks.setStartDate(tasks.getStartDate());
        tasks.setFinishDate(tasks.getFinishDate());

        users.add(tasks);

        System.out.println("users Object" + users);

        userService.createTask(tasks);

        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public  String delete(@PathVariable int id){
        userService.delete(id);
        return "redirect:/home";
    }


    @GetMapping("/update/{id}")
    public String preUpdate(@PathVariable int id , Model model, Principal principal ){

        Users users = userService.findByUserName(principal.getName());

       Tasks tasks = userService.getTaskById(id);

        model.addAttribute("tasks", tasks);


        System.out.println("redirecting to update form");
        return "updatePage";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Tasks tasks){

        Tasks existingTasks = userService.getTaskById(id);
        existingTasks.setTitle(tasks.getTitle());


        userService.update(existingTasks);


        System.out.println("update reached here");

        return "redirect:/home";
    }

}
