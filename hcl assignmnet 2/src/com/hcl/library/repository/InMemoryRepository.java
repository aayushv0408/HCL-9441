package com.hcl.library.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> entities = new LinkedHashMap<ID, T>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        entities.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entities.get(id));
    }

    public Collection<T> findAll() {
        return entities.values();
    }
}
