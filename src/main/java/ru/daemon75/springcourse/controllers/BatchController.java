package ru.daemon75.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.daemon75.springcourse.dao.UserDao;

 @Controller
 @RequestMapping("/test-batch")

public class BatchController {
    private final UserDao userDao;

    public BatchController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String index() {
        return "/batch/test-batch-menu";
    }
/*
    @GetMapping("/simple")
    public String updateSimple() {
        userDao.testMassiveSimpleUpdate();
        return "redirect:/users";
    }

    @GetMapping("/batch")
    public String updateBatch() {
        userDao.testMassiveBatchUpdate();
        return "redirect:/users";
    }

    @GetMapping("/erase")
    public String eraseTestUsers() {
        userDao.eraseTestUsers();
        return "redirect:/users";
    }

 */
}
