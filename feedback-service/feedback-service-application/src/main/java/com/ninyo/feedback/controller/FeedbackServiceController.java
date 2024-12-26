package com.ninyo.feedback.controller;

import com.ninyo.common.crudcore.controller.BaseCrudController;
import com.ninyo.common.crudcore.responses.IdResponse;
import com.ninyo.common.crudcore.responses.ListResponse;
import com.ninyo.feedback.config.FeedbackConfig;
import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.model.Index;
import com.ninyo.feedback.model.Status;
import com.ninyo.feedback.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackServiceController extends BaseCrudController<String, FeedbackDto, Feedback, FeedbackService> {

    private static final String ID_PATTERN = "/{id}";

    private static final String NAME_PATTERN = "/{name}";

    private final FeedbackService feedbackService;

    private final FeedbackConfig feedbackConfig;

    public FeedbackServiceController(FeedbackService feedbackService, FeedbackConfig feedbackConfig) {
        super(feedbackService);
        this.feedbackService = feedbackService;
        this.feedbackConfig = feedbackConfig;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse<String> create(@RequestBody FeedbackDto dto) {
        dto.setCreatedAt(Instant.now());
        dto.setUpdatedAt(Instant.now());
        dto.setStatus(Status.OPEN.toString());
        return super.create(dto);
    }

    @Override
    @PutMapping(value = ID_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody FeedbackDto dto) {
        dto.setUpdatedAt(Instant.now());
        service.update(id, dto);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<FeedbackDto> list() {
        if (!feedbackService.indexExists(feedbackConfig.getIndexName())) {
            return new ListResponse<>(List.of());
        }
        return super.list();
    }

    @PostMapping("/index")
    @ResponseStatus(HttpStatus.CREATED)
    public void createIndex(@RequestBody Index index) {
        feedbackService.createIndex(index.getName());
    }

    @DeleteMapping("/index" + NAME_PATTERN)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndex(@PathVariable String name) {
        feedbackService.deleteIndex(name);
    }

    @GetMapping("/index" + NAME_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public boolean indexExists(@PathVariable String name) {
        return feedbackService.indexExists(name);
    }

}