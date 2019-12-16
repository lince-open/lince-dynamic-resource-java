package work.lince.dynamic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.commons.exception.NotFoundException;
import work.lince.dynamic.model.AttributeDefinition;
import work.lince.dynamic.model.ResourceDefinition;
import work.lince.dynamic.repository.AttributeDefinitionRepository;
import work.lince.dynamic.repository.ResourceDefinitionRepository;

import java.util.List;

@Slf4j
@Service
public class AttributeDefinitionService {

    @Autowired
    protected AttributeDefinitionRepository repository;

    public AttributeDefinition update(AttributeDefinition definition) {
        return repository.save(definition);
    }

    public AttributeDefinition create(AttributeDefinition definition) {
        definition.setId(null);
        return repository.save(definition);
    }

    public AttributeDefinition findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<AttributeDefinition> findAll() {
        return repository.findAll();
    }

}