package com.deaaz.deaazcore.controller;

import java.util.ArrayList;
import java.util.List;
import com.deaaz.deaazcore.bl.UserBL;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
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
public class UserController {

    private @Autowired UserBL userBL;

    @GetMapping("/users")
    public @ResponseBody List<UserDTO> getUserList(@RequestParam(required = false) List<String> value, @RequestParam(required = false) List<String> field, @RequestParam(required = false) List<OperatorType> operator) {
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
        return userBL.getUserList(criterias);
    }

    @GetMapping("/users/{id}")
    public @ResponseBody UserDTO getUser(@PathVariable int id) {
        return userBL.getUser(id);
    }

    @PutMapping("/users")
    public @ResponseBody UserDTO createUser(@RequestBody UserDTO user) {
        return userBL.createUser(user);
    }

    @PostMapping("/users")
    public @ResponseBody UserDTO updateUser(@RequestBody UserDTO user) {
        return userBL.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userBL.deleteUser(id);
    }

    @PostMapping("/users/signup")
    public void signUp(@RequestBody UserDTO user) {
        userBL.signUp(user);
    }


}
