package com.deaaz.deaazcore.dto;

import com.deaaz.deaazcore.dao.UserDAO;
import com.deaaz.deaazcore.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDTO implements Serializable {

    private static  final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime lastLogin;
    private boolean active;
    private String role;

    public UserDTO() {

    }


    public UserDTO(UserDAO userDAO) {
        this.id = userDAO.getId();
        this.username = userDAO.getUsername();
        this.password = userDAO.getPassword();
        this.email = userDAO.getEmail();
        this.lastLogin = DateUtils.DateToLocalDateTime(userDAO.getLastLogin());
        this.active = userDAO.isActive();
        this.role = userDAO.getRole();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
