package ru.daemon75.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.daemon75.springcourse.models.User;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String URL = "jdbc:postgresql://localhost:5432/spring_db";
    private static final String USERNAME = "spring";
    private static final String PASSWORD = "spring";

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> allUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users VALUES (1,?,?,?)",
                user.getName(), user.getAge(), user.getEmail());
    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?",
                user.getName(), user.getAge(), user.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    /////////////////////
    //// Test Batch Update method
    /////////////////////

    public void testMassiveSimpleUpdate() {
        List<User> userList = create1000users();
        long timeBefore = System.currentTimeMillis();
        for (User user : userList) {
            jdbcTemplate.update("INSERT INTO users VALUES (?, ?, ?, ?)",
                    user.getId(), user.getName(), user.getAge(), user.getEmail());
        }
        long timeAfter = System.currentTimeMillis();
        System.out.println("Time: " + (timeAfter - timeBefore));
    }

    private List<User> create1000users() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User(i + 1000, "user" + i, 33, "user" + i + "@mail.com"));
        }
        return users;
    }

    public void testMassiveBatchUpdate() {
        List<User> userList = create1000users();
        long timeBefore = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO users VALUES (?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, userList.get(i).getId());
                ps.setString(2, userList.get(i).getName());
                ps.setInt(3, userList.get(i).getAge());
                ps.setString(4, userList.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });
        long timeAfter = System.currentTimeMillis();
        System.out.println("Time: " + (timeAfter - timeBefore));
    }

    public void eraseTestUsers() {
        jdbcTemplate.update("DELETE FROM users WHERE id>?", 999);
    }
}