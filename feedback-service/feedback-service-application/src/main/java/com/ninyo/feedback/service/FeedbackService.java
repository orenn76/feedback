package com.ninyo.feedback.service;

import com.ninyo.common.crudcore.service.BaseCrudService;
import com.ninyo.feedback.mappers.FeedbackDtoToEntityMapper;
import com.ninyo.feedback.mappers.FeedbackEntityToDtoConverter;
import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.repository.FeedbackRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService extends BaseCrudService<
        String,
        FeedbackDto,
        Feedback,
        FeedbackRepository,
        FeedbackDtoToEntityMapper,
        FeedbackEntityToDtoConverter> {

    private final ElasticsearchOperations elasticsearchOperations;

    public FeedbackService(ElasticsearchOperations elasticsearchOperations,
                           FeedbackRepository feedbackRepository,
                           FeedbackDtoToEntityMapper feedbackDtoToEntityMapper,
                           FeedbackEntityToDtoConverter feedbackEntityToDtoConverter) {
        super(feedbackRepository, feedbackDtoToEntityMapper, feedbackEntityToDtoConverter);
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void createIndex(String indexName) {
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).create();
    }

    public void deleteIndex(String indexName) {
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    public boolean indexExists(String indexName) {
        return elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).exists();
    }
}
