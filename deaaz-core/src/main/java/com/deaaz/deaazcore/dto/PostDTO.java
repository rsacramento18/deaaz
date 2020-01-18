package com.deaaz.deaazcore.dto;

import com.deaaz.deaazcore.dao.PostDAO;
import com.deaaz.deaazcore.utils.DBUtils;
import com.deaaz.deaazcore.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private WriterDTO writer;
    private String title;
    private String subtitle;
    private String post;
    private boolean active;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;

    public PostDTO() {

    }

    public PostDTO(PostDAO postDAO) {
        this.id = postDAO.getId();
        this.writer = new WriterDTO(postDAO.getWriter());
        this.title = postDAO.getTitle();
        this.subtitle = postDAO.getSubtitle();
        this.post = postDAO.getPost();
        this.active = postDAO.isActive();
        this.creationDate = DateUtils.DateToLocalDateTime(postDAO.getCreationDate());
        this.createdBy = postDAO.getCreatedBy();
        this.updatedDate = DateUtils.DateToLocalDateTime(postDAO.getUpdatedDate());
        this.updatedBy = postDAO.getUpdatedBy();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WriterDTO getWriter() {
        return writer;
    }

    public void setWriter(WriterDTO writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
