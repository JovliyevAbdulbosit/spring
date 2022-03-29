package com.security.jwtlearning.services;

import com.security.jwtlearning.domen.EntityModel;
import com.security.jwtlearning.repository.EntityModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityModelService {
    private final EntityModelRepository entityModelRepository;

    public EntityModelService(EntityModelRepository entityModelRepository) {
        this.entityModelRepository = entityModelRepository;
    }
    public List<EntityModel> getAll(){
        return entityModelRepository.findAll();
    }

    public EntityModel create(EntityModel entityModel) {
       return entityModelRepository.save(entityModel);
    }
}

