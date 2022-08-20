package ru.daemon75.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.daemon75.springcourse.models.User;
import ru.daemon75.springcourse.services.UsersService;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User existUser = usersService.getByEmail(user.getEmail());
        if ((existUser != null) && (existUser.getId() != user.getId()))
            errors.rejectValue("email", "", "This email is already using");
    }
}
