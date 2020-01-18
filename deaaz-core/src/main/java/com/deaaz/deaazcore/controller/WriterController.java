package com.deaaz.deaazcore.controller;

import java.util.ArrayList;
import java.util.List;
import com.deaaz.deaazcore.bl.UserBL;
import com.deaaz.deaazcore.bl.WriterBL;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.WriterDTO;
import com.deaaz.deaazcore.refs.OperatorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WriterController {

    private @Autowired WriterBL writerBL;

    @GetMapping("/writers")
    public @ResponseBody List<WriterDTO> getWriterList(@RequestParam(required = false) List<String> value, @RequestParam(required = false) List<String> field, @RequestParam(required = false) List<OperatorType> operator) {
        List<CriteriaDTO> criterias = new ArrayList<>();

        if(field != null && value != null && operator != null) {
            if(value.size() != field.size() || value.size() != operator.size()){
                return null;
            }

            for(int i = 0; i < value.size(); i++){
                CriteriaDTO criteria = new CriteriaDTO();

                criteria.getValues().add(value.get(i));
                criteria.setField(field.get(i));
                criteria.setOperator(operator.get(i));

                criterias.add(criteria);
            }
        }
        return writerBL.getWriterList(criterias);
    }

    @GetMapping("/writers/{id}")
    public @ResponseBody WriterDTO getWriter(@PathVariable int id) {
        return writerBL.getWriter(id);
    }

    @PutMapping("/writers")
    public @ResponseBody WriterDTO createWriter(@RequestBody WriterDTO writer) {
        return writerBL.createWriter(writer);
    }

    @PostMapping("/writers")
    public @ResponseBody WriterDTO updateWriter(@RequestBody WriterDTO writer) {
        return writerBL.updateWriter(writer);
    }

    @DeleteMapping("/writers/{id}")
    public void deleteWriter(@PathVariable int id) {
        writerBL.deleteWriter(id);
    }

}
