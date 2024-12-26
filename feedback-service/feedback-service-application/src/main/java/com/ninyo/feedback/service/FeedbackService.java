package com.ninyo.feedback.service;

import com.ninyo.common.crudcore.service.BaseCrudService;
import com.ninyo.feedback.config.FeedbackConfig;
import com.ninyo.feedback.mappers.FeedbackDtoToEntityMapper;
import com.ninyo.feedback.mappers.FeedbackEntityToDtoConverter;
import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.repository.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.query_dsl.MatchQuery;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.data.client.osc.NativeQuery;
import org.opensearch.data.client.osc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FeedbackService extends BaseCrudService<
        String,
        FeedbackDto,
        Feedback,
        FeedbackRepository,
        FeedbackDtoToEntityMapper,
        FeedbackEntityToDtoConverter> {

    private static final String STANDARD = "standard";
    private final ElasticsearchOperations operations;

    private final FeedbackConfig feedbackConfig;

    public FeedbackService(ElasticsearchOperations operations,
                           FeedbackRepository feedbackRepository,
                           FeedbackDtoToEntityMapper feedbackDtoToEntityMapper,
                           FeedbackEntityToDtoConverter feedbackEntityToDtoConverter, FeedbackConfig feedbackConfig) {
        super(feedbackRepository, feedbackDtoToEntityMapper, feedbackEntityToDtoConverter);
        this.operations = operations;
        this.feedbackConfig = feedbackConfig;
    }

    public List<SearchHit<Feedback>> listAutoSuggests(String fieldName, String partialValue) {
        MatchQuery matchQuery = new MatchQuery.Builder().field(fieldName).query(FieldValue.of(partialValue)).analyzer(STANDARD).build();
        Query query = Query.of(q -> q.match(matchQuery));

        NativeQuery searchQuery = new NativeQueryBuilder().withQuery(query).build();
        SearchHits<Feedback> feedbackHits = operations.search(searchQuery, Feedback.class, IndexCoordinates.of(feedbackConfig.getIndexName()));

        log.info("Feedback hits {} {}", feedbackHits.getSearchHits().size(), feedbackHits.getSearchHits());

        return feedbackHits.getSearchHits();
    }

}
