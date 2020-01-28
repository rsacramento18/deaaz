package com.deaaz.deaazcore.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.deaaz.deaazcore.dal.UserDAL;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.dto.CriteriaDTO;

import com.deaaz.deaazcore.refs.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBL {

    private @Autowired UserDAL userDAL;

    private @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserDTO> getUserList(List<CriteriaDTO> criterias) {
        List<UserDTO> users = userDAL.getUserList(criterias);
        users = users
                .stream()
                .map(x -> {
                        x.setPassword("");
                        return x;
                })
                .collect(Collectors.toList());

        return users;
    }


    public UserDTO getUser(int id) {
        return userDAL.getUser(id);
    }

    public UserDTO createUser(UserDTO user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDAL.createUser(user);
    }

    public UserDTO updateUser(UserDTO user) {
        return userDAL.updateUser(user);
    }

    public void deleteUser(int id) {
        UserDTO userDTO = userDAL.getUser(id);
        userDTO.setActive(false);
        userDAL.updateUser(userDTO);
    }

    public void signUp(UserDTO userDTO){
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDAL.createUser(userDTO);
    }

}
