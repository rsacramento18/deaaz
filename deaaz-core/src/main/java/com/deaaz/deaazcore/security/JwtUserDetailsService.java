package com.deaaz.deaazcore.security;

import com.deaaz.deaazcore.dal.UserDAL;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.refs.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAL userDAL;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<CriteriaDTO> criterias = new ArrayList<>();
        CriteriaDTO criteria = new CriteriaDTO(username, "username", OperatorType.EQUAL);

        UserDTO userDTO = userDAL.getUserList(criterias).get(0);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(),
                new ArrayList<>());
    }

}
