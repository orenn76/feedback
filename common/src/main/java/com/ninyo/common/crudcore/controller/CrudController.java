package com.ninyo.common.crudcore.controller;

import com.ninyo.common.crudcore.responses.DtoResponse;
import com.ninyo.common.crudcore.responses.IdResponse;
import com.ninyo.common.crudcore.responses.ListResponse;

public interface CrudController<ID, DTO> {

    IdResponse<ID> create(DTO dto);

    DtoResponse<DTO> read(ID id);

    ListResponse<DTO> list();

    void update(ID id, DTO dto);

}