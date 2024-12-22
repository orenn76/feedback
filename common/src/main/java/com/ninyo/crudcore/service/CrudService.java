package com.ninyo.crudcore.service;

import com.ninyo.crudcore.model.BaseEntity;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.List;

public interface CrudService<ID, DTO, ENTITY extends BaseEntity<ID>> {

    ID create(DTO dto);

    DTO read(ID id);

    List<DTO> list();

    void update(ID id, DTO dto);

    void delete(ID id);

}
