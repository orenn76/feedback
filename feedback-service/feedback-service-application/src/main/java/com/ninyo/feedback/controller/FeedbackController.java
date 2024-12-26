package com.ninyo.feedback.controller;

import com.ninyo.common.crudcore.controller.BaseCrudController;
import com.ninyo.common.crudcore.responses.IdResponse;
import com.ninyo.common.crudcore.responses.ListResponse;
import com.ninyo.feedback.config.FeedbackConfig;
import com.ninyo.feedback.mappers.FeedbackEntityToDtoConverter;
import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.model.Status;
import com.ninyo.feedback.service.FeedbackService;
import com.ninyo.feedback.service.IndexService;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController extends BaseCrudController<String, FeedbackDto, Feedback, FeedbackService> {

    private static final String ID_PATTERN = "/{id}";

    private final IndexService indexService;

    private final FeedbackConfig feedbackConfig;

    private final FeedbackEntityToDtoConverter entityToDtoConverter;

    public FeedbackController(FeedbackService feedbackService, IndexService indexService, FeedbackConfig feedbackConfig, FeedbackEntityToDtoConverter entityToDtoConverter) {
        super(feedbackService);
        this.indexService = indexService;
        this.feedbackConfig = feedbackConfig;
        this.entityToDtoConverter = entityToDtoConverter;
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
        super.update(id, dto);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<FeedbackDto> list() {
        if (!indexService.indexExists(feedbackConfig.getIndexName())) {
            return new ListResponse<>(List.of());
        }
        return super.list();
    }

    @GetMapping("/autosuggests")
    @ResponseStatus(HttpStatus.OK)
    List<FeedbackDto> listAutoSuggests(@RequestParam("fieldname") String fieldName, @RequestParam("partialvalue") String partialValue) {
        List<SearchHit<Feedback>> hitList = service.listAutoSuggests(fieldName, partialValue);
        return hitList.stream().map(SearchHit::getContent).map(entityToDtoConverter::convert).toList();
    }

}