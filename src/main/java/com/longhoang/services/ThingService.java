package com.longhoang.services;

import com.longhoang.models.Thing;

public interface ThingService {
    Iterable<Thing> findAll();

    Thing findById(Long id);

    void remove(Long id);

    void save(Thing thing);
}
