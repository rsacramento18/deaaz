package com.deaaz.deaazcore.dao;

import com.deaaz.deaazcore.dto.WriterDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "writer")
public class WriterDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserDAO user;

    @Column(name = "letter")
    private char letter;

    @Column(name = "color")
    private String color;

    @Column(name = "active")
    private boolean active;

    @Column(name = "creation_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

    public WriterDAO() {

    }

    public WriterDAO(WriterDTO writerDTO) {
        this.id = writerDTO.getId();
        this.user = new UserDAO(writerDTO.getUser());
        this.letter = writerDTO.getLetter();
        this.color = writerDTO.getColor();
        this.active = writerDTO.isActive();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
