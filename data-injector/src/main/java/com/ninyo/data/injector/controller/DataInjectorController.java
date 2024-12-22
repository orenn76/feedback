package com.ninyo.data.injector.controller;

import com.ninyo.data.injector.service.DataInjectorService;
import com.ninyo.post.model.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
public class DataInjectorController {

    @Autowired
    private DataInjectorService dataInjectorService;

    @RequestMapping(value = {"/data-injector/generate"}, method = {GET})
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam(value = "number", required = false) Integer number) {
        PostDto postDto = PostDto.builder().name("name").description("description").amount(100).build();
        this.dataInjectorService.create(postDto);
        log.info("Created pre_calc post");
    }
}
