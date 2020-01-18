package com.deaaz.deaazcore.repository;

import com.deaaz.deaazcore.dao.WriterDAO;
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
public class WriterRepository{

    @Autowired
    private EntityManager entityManager;

    public List<WriterDAO> findWriters(List<CriteriaDTO> criterias) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WriterDAO> query = builder.createQuery(WriterDAO.class);
        Root<WriterDAO> root = query.from(WriterDAO.class);
        Predicate[] predicates = DBUtils.createPredicates(criterias, builder, root);

        query.select(root);
        query.where(builder.and(predicates));

        return entityManager.createQuery(query).getResultList();
    }

    public WriterDAO getWriter( int id ) {
        WriterDAO writerDAO = entityManager.find(WriterDAO.class, id);
        return writerDAO;
    }

    public boolean createWriter(WriterDAO writer) {
        writer.setCreatedBy("DEAAZAPP");
        writer.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        writer.setUpdatedBy("DEAAZAPP");
        writer.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        entityManager.persist(writer);

        return true;
    }

    public boolean updateWriter(WriterDAO writer) {
        writer.setUpdatedBy("DEAAZAPP");
        writer.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        entityManager.persist(writer);

        return true;
    }

    public boolean deleteWriter(int id) {
        WriterDAO writerDAO = entityManager.find(WriterDAO.class, id);
        entityManager.remove(writerDAO);

        return true;
    }
}
