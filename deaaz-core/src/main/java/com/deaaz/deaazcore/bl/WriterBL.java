package com.deaaz.deaazcore.bl;

import com.deaaz.deaazcore.dal.WriterDAL;
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
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<WriterDTO> getWriterList(List<CriteriaDTO> criterias) {
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
