package com.ninyo.data.injector.controller;

import com.ninyo.data.injector.service.DataInjectorService;
import com.ninyo.feedback.model.FeedbackDto;
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
    public void create(@RequestParam(value = "description", required = false) String description) {
        FeedbackDto feedbackDto = FeedbackDto.builder().type("type").title("title").description("description").status("status").build();
        this.dataInjectorService.create(feedbackDto);
        log.info("Created feedback");
    }
}
