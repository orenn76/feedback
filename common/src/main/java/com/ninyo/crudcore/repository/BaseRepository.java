package com.ninyo.crudcore.repository;

import com.ninyo.crudcore.model.BaseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BaseRepository<ID, ENTITY extends BaseEntity<ID>> extends ElasticsearchRepository<ENTITY, ID> {
}
