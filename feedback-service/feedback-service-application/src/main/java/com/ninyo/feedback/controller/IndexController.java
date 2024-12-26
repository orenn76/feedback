package com.ninyo.feedback.controller;

import com.ninyo.feedback.model.Index;
import com.ninyo.feedback.service.IndexService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexController {

    private static final String NAME_PATTERN = "/{name}";

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createIndex(@RequestBody Index index) throws IOException {
        indexService.createIndexWithSettingsAndMappings(index.getName());
    }

    @DeleteMapping(NAME_PATTERN)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndex(@PathVariable String name) {
        indexService.deleteIndex(name);
    }

    @GetMapping(NAME_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public boolean indexExists(@PathVariable String name) {
        return indexService.indexExists(name);
    }

}