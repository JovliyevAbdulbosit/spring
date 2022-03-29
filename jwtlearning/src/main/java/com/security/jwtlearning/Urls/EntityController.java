package com.security.jwtlearning.Urls;

import com.security.jwtlearning.domen.EntityModel;
import com.security.jwtlearning.services.EntityModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new")
public class EntityController {
    private final EntityModelService entityModelService;

    public EntityController(EntityModelService entityModelService) {
        this.entityModelService = entityModelService;
    }

    @GetMapping("/test")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(entityModelService.getAll());
    }
    @PostMapping("/test")
    public ResponseEntity add(@RequestBody EntityModel entityModel){
        return ResponseEntity.ok(entityModelService.create(entityModel));
    }
}
