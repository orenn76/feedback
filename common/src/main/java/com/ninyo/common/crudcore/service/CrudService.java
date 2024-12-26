package com.ninyo.common.crudcore.service;

import com.ninyo.common.crudcore.model.BaseEntity;

import java.util.List;

public interface CrudService<ID, DTO, ENTITY extends BaseEntity<ID>> {

    ID create(DTO dto);

    DTO read(ID id);

    List<DTO> list();

    void update(ID id, DTO dto);

    void delete(ID id);

}
