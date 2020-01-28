package com.deaaz.deaazcore.controller;

import com.deaaz.deaazcore.bl.UserBL;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.refs.OperatorType;
import com.deaaz.deaazcore.security.JwtRequest;
import com.deaaz.deaazcore.security.JwtResponse;
import com.deaaz.deaazcore.security.JwtToken;
import com.deaaz.deaazcore.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserBL userBL;

    @PostMapping(value = "/authenticate")
    public UserDTO createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse response) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtToken.generateToken(userDetails);

        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(4*60*60);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        List<CriteriaDTO> criterias = new ArrayList<>();
        CriteriaDTO criteria = new CriteriaDTO(authenticationRequest.getUsername(), "username", OperatorType.EQUAL);
        criterias.add(criteria);

        List<UserDTO> user = userBL.getUserList(criterias);

        return user.get(0);

    }

    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);

        }
    }
}
