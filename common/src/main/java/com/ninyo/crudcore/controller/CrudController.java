package com.ninyo.crudcore.controller;

import com.ninyo.crudcore.responses.DtoResponse;
import com.ninyo.crudcore.responses.IdResponse;
import com.ninyo.crudcore.responses.ListResponse;

public interface CrudController<ID, DTO> {

    IdResponse<ID> create(DTO dto);

    DtoResponse<DTO> read(ID id);

    ListResponse<DTO> list();

    void update(ID id, DTO dto);

}