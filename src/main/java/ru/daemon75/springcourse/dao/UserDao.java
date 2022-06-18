package ru.daemon75.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.daemon75.springcourse.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserDao {
    private List<User> users;
    AtomicInteger counter = new AtomicInteger();

    {
        users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(),"John", 23, "john@mail.com"));
        users.add(new User(counter.incrementAndGet(),"Bob", 22, "bob@email.com"));
        users.add(new User(counter.incrementAndGet(),"Mary", 21, "mary@mail.com"));
        users.add(new User(counter.incrementAndGet(),"Katy", 21, "katy@mail.com"));
    }

    public List<User> allUsers(){
        return users;
    }

    public User show(int id){
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public User update(int id, User user) {
        show(id).setName(user.getName());
        show(id).setAge(user.getAge());
        show(id).setEmail(user.getEmail());
        return show(id);
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
