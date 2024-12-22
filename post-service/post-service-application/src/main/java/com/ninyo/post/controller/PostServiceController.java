package com.ninyo.post.controller;

import com.ninyo.crudcore.controller.BaseCrudController;
import com.ninyo.post.model.Index;
import com.ninyo.post.model.Post;
import com.ninyo.post.model.PostDto;
import com.ninyo.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostServiceController extends BaseCrudController<String, PostDto, Post, PostService> {

    private static final String NAME_PATTERN = "/{name}";

    private final PostService postService;

    public PostServiceController(PostService postService) {
        super(postService);
        this.postService = postService;
    }

    @PostMapping("/index")
    @ResponseStatus(HttpStatus.CREATED)
    public void createIndex(@RequestBody Index index) {
        postService.createIndex(index.getName());
    }

    @DeleteMapping("/index" + NAME_PATTERN)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndex(@PathVariable String name) {
        postService.deleteIndex(name);
    }

    @GetMapping("/index" + NAME_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public boolean indexExists(@PathVariable String name) {
        return postService.indexExists(name);
    }
}