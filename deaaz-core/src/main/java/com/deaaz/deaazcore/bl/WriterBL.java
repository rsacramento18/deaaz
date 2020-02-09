package com.deaaz.deaazcore.bl;

import com.deaaz.deaazcore.dal.UserDAL;
import com.deaaz.deaazcore.dal.WriterDAL;
import com.deaaz.deaazcore.dao.UserDAO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.dto.WriterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriterBL {

    private @Autowired
    WriterDAL writerDAL;

    private @Autowired
    UserDAL userDAL;

    private @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<WriterDTO> getWriterList(List<CriteriaDTO> criterias) {
        CriteriaDTO newCriteria;
        for (CriteriaDTO criteria: criterias) {
            if(criteria.getField().equals("user")) {
                UserDTO userDTO = userDAL.getUser(Integer.parseInt(criteria.getValues().get(0).toString()));

                UserDAO userDAO = new UserDAO(userDTO);

                criteria.setValues().add(userDAO);
            }
        }
        return writerDAL.getWriterList(criterias);
    }

    public WriterDTO getWriter(int id) {
        return writerDAL.getWriter(id);
    }

    public WriterDTO createWriter(WriterDTO writer) {
        return writerDAL.createWriter(writer);
    }

    public WriterDTO updateWriter(WriterDTO writer) {
        return writerDAL.updateWriter(writer);
    }

    public void deleteWriter(int id) {
        WriterDTO writerDTO = writerDAL.getWriter(id);
        writerDTO.setActive(false);
        writerDAL.updateWriter(writerDTO);
    }
}
