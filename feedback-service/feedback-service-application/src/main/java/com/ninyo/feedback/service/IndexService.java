package com.ninyo.feedback.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class IndexService {

    private final ElasticsearchOperations operations;
    private final ObjectMapper objectMapper;

    @Value("classpath:indices/settings.json")
    private File settingsJson;

    @Value("classpath:indices/mappings.json")
    private File mappingsJson;

    public IndexService(ElasticsearchOperations operations, ObjectMapper objectMapper) {
        this.operations = operations;
        this.objectMapper = objectMapper;
    }

    public void createIndex(String indexName) {
        operations.indexOps(IndexCoordinates.of(indexName)).create();
    }

    public void createIndexWithSettingsAndMappings(String indexName) throws IOException {
        Map<String, Object> settings = objectMapper.readValue(settingsJson, new TypeReference<>() {
        });
        Map<String, Object> mappings = objectMapper.readValue(mappingsJson, new TypeReference<>() {
        });
        operations.indexOps(IndexCoordinates.of(indexName)).create(Document.from(settings), Document.from(mappings));
    }

    public void deleteIndex(String indexName) {
        operations.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    public boolean indexExists(String indexName) {
        return operations.indexOps(IndexCoordinates.of(indexName)).exists();
    }

}
