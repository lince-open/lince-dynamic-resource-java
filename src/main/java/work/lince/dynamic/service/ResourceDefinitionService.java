package work.lince.dynamic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.commons.exception.NotFoundException;
import work.lince.dynamic.model.ResourceAttributeDefinition;
import work.lince.dynamic.model.ResourceDefinition;
import work.lince.dynamic.repository.ResourceAttributeDefinitionRepository;
import work.lince.dynamic.repository.ResourceDefinitionRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ResourceDefinitionService {

    @Autowired
    protected ResourceDefinitionRepository repository;

    @Autowired
    protected ResourceAttributeDefinitionRepository resourceAttributeDefinitionRepository;

    public ResourceDefinition createOrUpdate(ResourceDefinition definition) {
        Optional<ResourceDefinition> resourceDefinition = repository.findByExternalCode(definition.getExternalCode());
        return resourceDefinition.isPresent() ? this.update(resourceDefinition.get(), definition) : this.create(definition);
    }

    protected ResourceDefinition create(ResourceDefinition definition) {
        definition.setAttributes(null);
        return repository.save(definition);
    }

    protected ResourceDefinition update(ResourceDefinition definition, ResourceDefinition definitionNew) {
        definition.setName(definitionNew.getName());
        definition.setDescription(definitionNew.getDescription());
        return repository.save(definition);
    }

    public ResourceAttributeDefinition addAttribute(String externalCode, ResourceAttributeDefinition resourceAttribute) {
        ResourceDefinition resourceDefinition = this.findByExternalCode(externalCode);
        ResourceAttributeDefinition resourceAttributeDefinition = resourceAttributeDefinitionRepository.save(resourceAttribute);
        resourceDefinition.getAttributes().add(resourceAttributeDefinition);
        repository.save(resourceDefinition);
        return resourceAttributeDefinition;
    }

    public ResourceDefinition findByExternalCode(String externalCode) {
        return repository.findByExternalCode(externalCode)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<ResourceDefinition> findAll() {
        return repository.findAll();
    }

}