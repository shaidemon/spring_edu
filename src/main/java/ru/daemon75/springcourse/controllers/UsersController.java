package ru.daemon75.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.daemon75.springcourse.dao.UserDao;
import ru.daemon75.springcourse.models.User;
import ru.daemon75.springcourse.util.UserValidator;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDao userDao;
    private final UserValidator userValidator;

    @Autowired
    public UsersController(UserDao userDao, UserValidator userValidator) {
        this.userDao = userDao;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String allUsers(Model model) {
        // get All users from DAO and transfer to view
        model.addAttribute("users", userDao.allUsers());
        return "users/allUsers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // get User by Id and transfer to view
        model.addAttribute("user", userDao.getById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/users/new";
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.getById(id));
        return "/users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/users/edit";
        userDao.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}
