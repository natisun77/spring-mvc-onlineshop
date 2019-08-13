package com.nataliia.spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPayload {
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Size(min = 8)
    private String password;
    @NotNull
    @Size(min = 8)
    private String repeatedPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public static UserPayload fromUser(User user) {
        UserPayload userPayload = new UserPayload();
        userPayload.id = user.getId();
        userPayload.firstName = user.getFirstName();
        userPayload.lastName = user.getLastName();
        userPayload.email = user.getEmail();
        userPayload.password = user.getPassword();
        userPayload.repeatedPassword = user.getPassword();
        return userPayload;
    }
}
