package ru.daemon75.springcourse.models;

import javax.validation.constraints.*;

public class User {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 letters")
    private  String name;

    @Min(value = 0, message = "Age should be more than 0")
    private int age;

    @NotEmpty(message = " Email not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+(\\h*\\w*)*, [A-Z]\\w+\\h*\\w*, \\d{6}", message = "Address should be this format: Country, City, 6-digits-zipcode")
    private String address;


    public User(){
    }

    public User(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
