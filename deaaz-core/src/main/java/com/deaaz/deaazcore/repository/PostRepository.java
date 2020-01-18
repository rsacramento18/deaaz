package com.deaaz.deaazcore.repository;

import com.deaaz.deaazcore.dao.PostDAO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class PostRepository {

    @Autowired
    private EntityManager entityManager;

    public List<PostDAO> findPosts(List<CriteriaDTO> criterias) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostDAO> query = builder.createQuery(PostDAO.class);
        Root<PostDAO> root = query.from(PostDAO.class);
        Predicate[] predicates = DBUtils.createPredicates(criterias, builder, root);

        query.select(root);
        query.where(builder.and(predicates));

        return entityManager.createQuery(query).getResultList();
    }

    public PostDAO getPost(int id ) {
        PostDAO postDAO = entityManager.find(PostDAO.class, id);
        return postDAO;
    }

    public boolean createPost(PostDAO post) {
        post.setCreatedBy("DEAAZAPP");
        post.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        post.setUpdatedBy("DEAAZAPP");
        post.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        entityManager.persist(post);

        return true;
    }

    public boolean updatePost(PostDAO post) {
        post.setUpdatedBy("DEAAZAPP");
        post.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        entityManager.persist(post);

        return true;
    }

    public boolean deletePost(int id) {
        PostDAO postDAO = entityManager.find(PostDAO.class, id);
        entityManager.remove(postDAO);

        return true;
    }
}
