package ru.daemon75.springcourse.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.daemon75.springcourse.dao.UserDao;
import ru.daemon75.springcourse.models.User;
import ru.daemon75.springcourse.services.UsersService;

@Component
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;


    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String chooseAdmin(Model model, @ModelAttribute ("user") User user){
        model.addAttribute("users", usersService.getAll());
        return "admin/choose_admin";
    }

    @PatchMapping("/add")
    public String setAdmin(@ModelAttribute("user") User chosenUser){
        User admin = usersService.getById(chosenUser.getId());
        System.out.println("the Admins Id: " + admin.getId() + " and his name is " + admin.getName() );
        return "redirect:/users";
    }
}
