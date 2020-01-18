package com.deaaz.deaazcore.dal;

import com.deaaz.deaazcore.dao.PostDAO;
import com.deaaz.deaazcore.dao.WriterDAO;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.PostDTO;
import com.deaaz.deaazcore.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostDAL {

    private @Autowired
    PostRepository postRepository;

    public List<PostDTO> getPostList(List<CriteriaDTO> criterias) {
        List<PostDAO> postDAOList = postRepository.findPosts(criterias);

        return postDAOList.stream().map(this::toPostDTO).collect(Collectors.toList());
    }

    public PostDTO getPost(int id) {
        PostDAO postDAO = postRepository.getPost(id);

        return toPostDTO(postDAO);
    }

    public PostDTO createPost(PostDTO postDTO) {
        PostDAO postDAO = toPostDAO(postDTO);

        postRepository.createPost(postDAO);

        return toPostDTO(postDAO);
    }

    public PostDTO updatePost(PostDTO postDTO) {
        PostDAO postDAO = toPostDAO(postDTO);

        postRepository.updatePost(postDAO);

        return toPostDTO(postDAO);
    }

    public void deletePost(int id) {
        postRepository.deletePost(id);

    }

    private PostDTO toPostDTO(PostDAO postDAO) {
        return new PostDTO(postDAO);
    }


    private PostDAO toPostDAO(PostDTO postDTO) {
        Optional<Integer> postIdOpt = Optional.of(postDTO.getId());

        if(postIdOpt.isPresent() && postIdOpt.get() > 0) {
            PostDAO postDAO = postRepository.getPost(postDTO.getId());

            postDAO.setWriter(new WriterDAO(postDTO.getWriter()));
            postDAO.setTitle(postDTO.getTitle());
            postDAO.setSubtitle(postDTO.getSubtitle());
            postDAO.setPost(postDTO.getPost());
            postDAO.setActive(postDTO.isActive());

            return postDAO;
        } else {
            PostDAO  post = new PostDAO(postDTO);

            return post;
        }
    }
}
