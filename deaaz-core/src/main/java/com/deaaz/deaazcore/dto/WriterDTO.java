package com.deaaz.deaazcore.dto;

import com.deaaz.deaazcore.dao.WriterDAO;
import org.apache.catalina.User;

import java.io.Serializable;

public class WriterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private UserDTO user;
    private char letter;
    private String color;
    private boolean active;

    public WriterDTO() {

    }

    public WriterDTO(WriterDAO writerDAO) {
        this.id = writerDAO.getId();
        this.user = new UserDTO(writerDAO.getUser());
        this.letter = writerDAO.getLetter();
        this.color = writerDAO.getColor();
        this.active = writerDAO.isActive();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
