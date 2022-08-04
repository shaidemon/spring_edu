package ru.daemon75.springcourse.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.daemon75.springcourse.dao.UserDao;
import ru.daemon75.springcourse.models.User;

@Component
@RequestMapping("/admin")
public class AdminController {

    private final UserDao userDao;

    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String chooseAdmin(Model model, @ModelAttribute ("user") User user){
        model.addAttribute("users", userDao.allUsers());
        return "admin/choose_admin";
    }

    @PatchMapping("/add")
    public String setAdmin(@ModelAttribute("user") User chosenUser){
        User admin = userDao.getById(chosenUser.getId());
        System.out.println("the Admins Id: " + admin.getId() + " and his name is " + admin.getName() );
        return "redirect:/users";
    }
}
