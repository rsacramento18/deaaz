package com.deaaz.deaazcore.controller;

import com.deaaz.deaazcore.bl.PostBL;
import com.deaaz.deaazcore.dto.CriteriaDTO;
import com.deaaz.deaazcore.dto.PostDTO;
import com.deaaz.deaazcore.refs.OperatorType;
import com.deaaz.deaazcore.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private @Autowired
    PostBL postBL;

    @GetMapping("/posts")
    public @ResponseBody
    List<PostDTO> getPostList(@RequestParam(required = false) List<String> value, @RequestParam(required = false) List<String> field, @RequestParam(required = false) List<OperatorType> operator) {
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
        return postBL.getPostList(criterias);
    }

    @GetMapping("/posts/{id}")
    public @ResponseBody PostDTO getPost(@PathVariable int id) {
        return postBL.getPost(id);
    }

    @PutMapping("/posts")
    public @ResponseBody PostDTO createPost(@RequestBody PostDTO post) {
        return postBL.createPost(post);
    }

    @PostMapping("/posts")
    public @ResponseBody PostDTO updatePosts(@RequestBody PostDTO post) {
        return postBL.updatePost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        postBL.deletePost(id);
    }
}
