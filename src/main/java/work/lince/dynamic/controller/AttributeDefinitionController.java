package work.lince.dynamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.lince.dynamic.model.AttributeDefinition;
import work.lince.dynamic.service.AttributeDefinitionService;
import work.lince.dynamic.service.ResourceDefinitionService;

import java.util.List;

@RestController
@RequestMapping(path = "/definitions/attributes")
public class AttributeDefinitionController {

    @Autowired
    protected AttributeDefinitionService service;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttributeDefinition findById(@PathVariable("id") final Long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttributeDefinition> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttributeDefinition create(@RequestBody @Validated AttributeDefinition body) {
        return service.create(body);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttributeDefinition update(@PathVariable("id") final Long id, @RequestBody @Validated AttributeDefinition body) {
        body.setId(id);
        return service.update(body);
    }

}