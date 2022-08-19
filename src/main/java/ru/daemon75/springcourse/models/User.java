package ru.daemon75.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 letters")
//    @Column(name = "name")
    private  String name;

    @Min(value = 0, message = "Age should be more than 0")
//    @Column(name = "age")
    private int age;

    @NotEmpty(message = " Email not be empty")
    @Email(message = "Email must be valid")
//    @Column(name = "email")
    private String email;

//    @Column(name = "address")
    @Pattern(regexp = "[A-Z]\\w+(\\h*\\w*)*, [A-Z]\\w+\\h*\\w*, \\d{6}", message = "Address should be this format: Country, City, 6-digits-zipcode")
    private String address;


    public User(){
    }

    public User(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
