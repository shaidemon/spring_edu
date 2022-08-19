package ru.daemon75.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daemon75.springcourse.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

}
