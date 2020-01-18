package com.deaaz.deaazcore.dal;

import com.deaaz.deaazcore.dao.UserDAO;
import com.deaaz.deaazcore.dao.WriterDAO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.WriterDTO;
import com.deaaz.deaazcore.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WriterDAL {

    private @Autowired
    WriterRepository writerRepository;

    public List<WriterDTO> getWriterList(List<CriteriaDTO> criterias) {
        List<WriterDAO> writerDAOList = writerRepository.findWriters(criterias);

        return writerDAOList.stream().map(this::toWriterDTO).collect(Collectors.toList());
    }

    public WriterDTO getWriter(int id) {
        WriterDAO writerDAO = writerRepository.getWriter(id);

        return toWriterDTO(writerDAO);
    }

    public WriterDTO createWriter(WriterDTO writerDTO) {
        WriterDAO writerDAO = toWriterDAO(writerDTO);

        writerRepository.createWriter(writerDAO);

        return toWriterDTO(writerDAO);
    }

    public WriterDTO updateWriter(WriterDTO writerDTO) {
        WriterDAO writerDAO = toWriterDAO(writerDTO);

        writerRepository.updateWriter(writerDAO);

        return toWriterDTO(writerDAO);
    }

    public void deleteWriter(int id) {
        writerRepository.deleteWriter(id);

    }

    private WriterDTO toWriterDTO(WriterDAO writerDAO) {
//        WriterDTO  writerDTO = new WriterDTO(writerDAO);
        return new WriterDTO(writerDAO);
    }


    private WriterDAO toWriterDAO(WriterDTO writerDTO) {
        Optional<Integer> writerIdOpt = Optional.of(writerDTO.getId());

        if(writerIdOpt.isPresent() && writerIdOpt.get() > 0) {
            WriterDAO writerDAO = writerRepository.getWriter(writerDTO.getId());

            writerDAO.setUser(new UserDAO(writerDTO.getUser()));
            writerDAO.setLetter(writerDTO.getLetter());
            writerDAO.setColor(writerDTO.getColor());
            writerDAO.setActive(writerDTO.isActive());

            return writerDAO;
        } else {
            WriterDAO  writer = new WriterDAO(writerDTO);

            return writer;
        }
    }
}
