package com.ninyo.common.crudcore.service;

import com.ninyo.common.crudcore.mappers.Converter;
import com.ninyo.common.crudcore.mappers.Mapper;
import com.ninyo.common.crudcore.model.BaseEntity;
import com.ninyo.common.crudcore.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public abstract class BaseCrudService<
        ID,
        DTO,
        ENTITY extends BaseEntity<ID>,
        REPOSITORY extends BaseRepository<ID, ENTITY>,
        DTO_TO_ENTITY_MAPPER extends Mapper<DTO, ENTITY>,
        ENTITY_TO_DTO_CONVERTER extends Converter<ENTITY, DTO>>
        implements CrudService<ID, DTO, ENTITY> {

    protected final REPOSITORY repository;

    protected final DTO_TO_ENTITY_MAPPER dtoToEntityMapper;

    protected final ENTITY_TO_DTO_CONVERTER entityToDtoConverter;

    @SuppressWarnings("unchecked")
    public final Class<ENTITY> runtimeEntity = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[2];

    @Override
    public ID create(DTO dto) {
        log.info("Start creating entity");
        ENTITY entity = createInstanceOfEntity();
        dtoToEntityMapper.map(dto, entity);
        entity = repository.save(entity);
        log.info("Created entity and saved with id: {}", entity.getId());
        return entity.getId();
    }

    @Override
    public DTO read(ID id) {
        log.info("Start reading entity with id: {}", id);
        ENTITY entity = repository.findById(id).orElse(null);
        assertNotNull(entity, id);
        log.info("Read entity with id: {}", id);
        return entityToDtoConverter.convert(entity);
    }

    @Override
    public List<DTO> list() {
        log.info("Start listing entities");
        Iterable<ENTITY> entities = repository.findAll();
        List<DTO> dtoList = entityToDtoConverter.convert(entities);
        log.info("Read entities of size: {}", dtoList.size());
        return dtoList;
    }

    @Override
    public void update(ID id, DTO dto) {
        log.info("Start updating entity with id: {}", id);
        ENTITY entity = repository.findById(id).orElse(null);
        assertNotNull(entity, id);
        dtoToEntityMapper.map(dto, entity);
        repository.save(entity);
        log.info("Updated entity with id: {}", id);
    }

    @Override
    public void delete(ID id) {
        log.info("Start deleting entity with id: {}", id);
        repository.deleteById(id);
        log.info("Deleted entity with id: {}", id);
    }

    private ENTITY createInstanceOfEntity() {
        try {
            return runtimeEntity.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Could not create instance of class:" + runtimeEntity, e);
        }
    }

    private void assertNotNull(ENTITY entity, ID id) {
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Entity of type: " + runtimeEntity.getSimpleName() + " entity with id: " + id + " doesn't exist!");
        }
    }

    private void assertNotNull(ENTITY entity) {
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Entity of type: " + runtimeEntity.getSimpleName() + " doesn't exist!");
        }
    }

    private void assertNotNull(Iterable<ENTITY> entities) {
        if (entities == null || !entities.iterator().hasNext()) {
            throw new EntityNotFoundException("Entities of type: " + runtimeEntity.getSimpleName() + " were not found for input id list");
        }
        entities.forEach(this::assertNotNull);
    }

}
