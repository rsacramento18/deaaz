package com.deaaz.deaazcore.repository;

import com.deaaz.deaazcore.dao.UserDAO;
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
public class UserRepository {

   @Autowired
   private EntityManager entityManager;

   public List<UserDAO> findUsers(List<CriteriaDTO> criterias) {
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<UserDAO> query = builder.createQuery(UserDAO.class);
      Root<UserDAO> root = query.from(UserDAO.class);
      Predicate[] predicates = DBUtils.createPredicates(criterias, builder, root);

      query.select(root);
      query.where(builder.and(predicates));

      return entityManager.createQuery(query).getResultList();
   }

   public UserDAO getUser( int id ) {
      UserDAO userDAO = entityManager.find(UserDAO.class, id);
      return userDAO;
   }

   public boolean createUser(UserDAO user) {
      user.setCreatedBy("DEAAZAPP");
      user.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
      user.setUpdatedBy("DEAAZAPP");
      user.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

      entityManager.persist(user);

      return true;
   }

   public boolean updateUser(UserDAO user) {
      user.setUpdatedBy("DEAAZAPP");
      user.setUpdatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

      entityManager.persist(user);

      return true;
   }

   public boolean deleteUser(int id) {
      UserDAO userDAO = entityManager.find(UserDAO.class, id);
      entityManager.remove(userDAO);

      return true;
   }
}
