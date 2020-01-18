package com.deaaz.deaazcore.bl;

import com.deaaz.deaazcore.dal.PostDAL;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.PostDTO;
import com.deaaz.deaazcore.dto.UserDTO;
import com.deaaz.deaazcore.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostBL {

    private @Autowired
    PostDAL postDAL;

    private @Autowired
    AuthenticationService authenticationService;

    private @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static Logger LOGGER = LoggerFactory.getLogger(PostBL.class);

    public List<PostDTO> getPostList(List<CriteriaDTO> criterias) {

        List<PostDTO> posts = postDAL.getPostList(criterias);

        if(authenticationService.isAuthenticated()) {
            return posts;
        }

        for(PostDTO post : posts) {
            post.getWriter().setUser(new UserDTO());
        }

        return posts;

    }

    public PostDTO getPost(int id) {
        PostDTO post = postDAL.getPost(id);

        if(authenticationService.isAuthenticated()){
            return post;
        }

        post.getWriter().setUser(new UserDTO());

        return post;
    }

    public PostDTO createPost(PostDTO writer) {
        return postDAL.createPost(writer);
    }

    public PostDTO updatePost(PostDTO writer) {
        return postDAL.updatePost(writer);
    }

    public void deletePost(int id) {
        PostDTO postDTO = postDAL.getPost(id);
        postDTO.setActive(false);
        postDAL.updatePost(postDTO);
    }
}
