package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.config.AuditLogger;
import com.zuehlke.securesoftwaredevelopment.domain.*;
import com.zuehlke.securesoftwaredevelopment.repository.PostRepository;
import com.zuehlke.securesoftwaredevelopment.repository.CommentRepository;
import com.zuehlke.securesoftwaredevelopment.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {

    private static final Logger LOG = LoggerFactory.getLogger(PostsController.class);
    private static final AuditLogger auditLogger = AuditLogger.getAuditLogger(PersonRepository.class);

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private PersonRepository userRepository;

    public PostsController(PostRepository postRepository, CommentRepository commentRepository, PersonRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String showSearch(Model model) {
        model.addAttribute("posts", postRepository.getAll());
        return "posts";
    }

    @GetMapping(value = "/api/posts/search", produces = "application/json")
    @ResponseBody
    public List<Post> search(@RequestParam("query") String query) throws SQLException {
        return postRepository.search(query);
    }

    @GetMapping("/posts")
    public String showPost(@RequestParam(name = "id", required = false) String id, Model model) {
        if (id == null) {
            model.addAttribute("posts", postRepository.getAll());
            return "posts";
        }

        Post post = postRepository.findById(id);
        Person poster = userRepository.get(post.getUserId());

        model.addAttribute("post", post);
        model.addAttribute("user", getPersonName(poster));
        List<Comment> comments = commentRepository.getAll(id);

        List<ViewComment> commentList = new ArrayList<>();

        for (Comment comment : comments) {
            Person person = userRepository.get(comment.getUserId());
            commentList.add(new ViewComment(getPersonName(person), comment.getComment()));
        }

        model.addAttribute("comments", commentList);

        return "post";
    }

    private String getPersonName(Person person) {
        return person.getFirstName() + " " + person.getLastName();
    }
}
