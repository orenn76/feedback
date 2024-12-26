package com.ninyo.common.crudcore.repository;

import com.ninyo.common.crudcore.model.BaseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BaseRepository<ID, ENTITY extends BaseEntity<ID>> extends ElasticsearchRepository<ENTITY, ID> {
}
