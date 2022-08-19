package ru.daemon75.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.daemon75.springcourse.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
//    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u where u.email=:e", User.class).setParameter("e", email).
                getResultList().stream().findAny().orElse(null);
//                jdbcTemplate.query("SELECT * FROM users WHERE email=?", new BeanPropertyRowMapper<>(User.class), email)
//                .stream().findAny().orElse(null);
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
//        jdbcTemplate.update("INSERT INTO users (name, age, email, address) VALUES (?,?,?,?)",
//                user.getName(), user.getAge(), user.getEmail(), user.getAddress());
    }

    @Transactional
    public void update(int id, User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
//        jdbcTemplate.update("UPDATE users SET name = ?, age = ?, email = ?, address = ? WHERE id = ?",
//                user.getName(), user.getAge(), user.getEmail(), user.getAddress(), id);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
//        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

/*
    /////////////////////
    //// Test Batch Update method (JDBC Template)
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
            users.add(new User(i + 1000, "user" + i, 33, "user" + i + "@mail.com", "Some address"));
        }
        return users;
    }

    public void testMassiveBatchUpdate() {
        List<User> userList = create1000users();
        long timeBefore = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO users VALUES (?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, userList.get(i).getId());
                ps.setString(2, userList.get(i).getName());
                ps.setInt(3, userList.get(i).getAge());
                ps.setString(4, userList.get(i).getEmail());
                ps.setString(5, userList.get(i).getAddress());
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
 */
}