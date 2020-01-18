package com.deaaz.deaazcore.dal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.repository.UserRepository;
import com.deaaz.deaazcore.utils.DateUtils;

import com.deaaz.deaazcore.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAL {

    private @Autowired UserRepository userRepository;

    public List<UserDTO> getUserList(List<CriteriaDTO> criterias) {
        List<UserDAO> userDAOList = userRepository.findUsers(criterias);

        return userDAOList.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUser(int id) {
        UserDAO userDAO = userRepository.getUser(id);

        return toUserDTO(userDAO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserDAO userDAO = toUserDAO(userDTO);

        userRepository.createUser(userDAO);

        return toUserDTO(userDAO);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserDAO userDAO = toUserDAO(userDTO);

        userRepository.updateUser(userDAO);

        return toUserDTO(userDAO);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);

    }

    private UserDTO toUserDTO(UserDAO userDAO) {
        UserDTO userDTO = new UserDTO(userDAO);

        return userDTO;
    }


    private UserDAO toUserDAO(UserDTO userDTO) {
        Optional<Integer> userIdOpt = Optional.of(userDTO.getId());

        if(userIdOpt.isPresent() && userIdOpt.get() > 0) {
            UserDAO userDAO = userRepository.getUser(userDTO.getId());

            userDAO.setUsername(userDTO.getUsername());
            userDAO.setPassword(userDTO.getPassword());
            userDAO.setEmail(userDTO.getEmail());
            userDAO.setLastLogin(DateUtils.localDateTimeToDate(userDTO.getLastLogin()));
            userDAO.setActive(userDTO.isActive());
            userDAO.setRole(userDTO.getRole());

            return userDAO;
        } else {
            UserDAO user = new UserDAO(userDTO);

            return user;
        }
    }
}
