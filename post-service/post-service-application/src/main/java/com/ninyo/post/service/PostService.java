package com.ninyo.post.service;

import com.ninyo.crudcore.service.BaseCrudService;
import com.ninyo.post.mappers.PostDtoToEntityMapper;
import com.ninyo.post.mappers.PostEntityToDtoConverter;
import com.ninyo.post.model.Post;
import com.ninyo.post.model.PostDto;
import com.ninyo.post.repository.PostRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

@Service
public class PostService extends BaseCrudService<
        String,
        PostDto,
        Post,
        PostRepository,
        PostDtoToEntityMapper,
        PostEntityToDtoConverter> {

    private final ElasticsearchOperations elasticsearchOperations;

    public PostService(ElasticsearchOperations elasticsearchOperations,
                       PostRepository postRepository,
                       PostDtoToEntityMapper postDtoToEntityMapper,
                       PostEntityToDtoConverter postEntityToDtoConverter) {
        super(postRepository, postDtoToEntityMapper, postEntityToDtoConverter);
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
