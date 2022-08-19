package ru.daemon75.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.daemon75.springcourse.dao.UserDao;
import ru.daemon75.springcourse.models.User;
import ru.daemon75.springcourse.services.UsersService;
import ru.daemon75.springcourse.util.UserValidator;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final UserValidator userValidator;

    @Autowired
    public UsersController(UsersService usersService, UserValidator userValidator) {
        this.usersService = usersService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", usersService.getAll());
        return "users/allUsers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getById(id));
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
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", usersService.getById(id));
        return "/users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) return "/users/edit";
        usersService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}
