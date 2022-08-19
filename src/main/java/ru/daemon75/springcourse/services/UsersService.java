package ru.daemon75.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.springcourse.models.User;
import ru.daemon75.springcourse.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAll (){
        return usersRepository.findAll();
    }

    public User getById(int id) {
        Optional<User> user= usersRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        usersRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}
