package com.longhoang.services;

import com.longhoang.models.Thing;
import com.longhoang.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ThingServiceImpl implements ThingService {
    @Autowired
    private ThingRepository thingRepository;

    @Override
    public Iterable<Thing> findAll() {
        return thingRepository.findAll();
    }

    @Override
    public Thing findById(Long id) {
        return thingRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        thingRepository.delete(id);
    }

    @Override
    public void save(Thing thing) {
        thingRepository.save(thing);
    }
}
