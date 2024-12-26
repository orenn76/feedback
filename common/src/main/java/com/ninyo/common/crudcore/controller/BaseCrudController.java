package com.ninyo.common.crudcore.controller;

import com.ninyo.common.crudcore.model.BaseEntity;
import com.ninyo.common.crudcore.responses.DtoResponse;
import com.ninyo.common.crudcore.responses.IdResponse;
import com.ninyo.common.crudcore.responses.ListResponse;
import com.ninyo.common.crudcore.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseCrudController<
        ID,
        DTO,
        ENTITY extends BaseEntity<ID>,
        SERVICE extends CrudService<ID, DTO, ENTITY>>
        implements CrudController<ID, DTO> {

    private static final String ID_PATTERN = "/{id}";

    protected final SERVICE service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse<ID> create(@RequestBody DTO dto) {
        return new IdResponse<>(service.create(dto));
    }

    @Override
    @GetMapping(ID_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public DtoResponse<DTO> read(@PathVariable ID id) {
        DTO dto = service.read(id);
        return new DtoResponse<>(dto);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<DTO> list() {
        List<DTO> dtoList = service.list();
        return new ListResponse<>(dtoList);
    }

    @Override
    @PutMapping(ID_PATTERN)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable ID id, @RequestBody DTO dto) {
        service.update(id, dto);
    }

}
