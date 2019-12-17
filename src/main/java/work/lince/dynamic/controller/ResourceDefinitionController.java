package work.lince.dynamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.dynamic.dto.ResourceAttributeDTO;
import work.lince.dynamic.dto.ResourceDTO;
import work.lince.dynamic.model.AttributeDefinition;
import work.lince.dynamic.model.ResourceAttributeDefinition;
import work.lince.dynamic.model.ResourceDefinition;
import work.lince.dynamic.service.ResourceDefinitionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/definitions/resources")
public class ResourceDefinitionController {

    @Autowired
    protected ResourceDefinitionService service;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceDTO findById(@PathVariable("id") final String id) {
        return toDto(service.findByExternalCode(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResourceDTO> findAll() {
        List<ResourceDefinition> resourceDefinitionList = service.findAll();
        if (CollectionUtils.isEmpty(resourceDefinitionList)) {
            return Collections.EMPTY_LIST;
        }
        return resourceDefinitionList.stream().map(this::toDto).collect(Collectors.toList());

    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceDTO createOrUpdate(@PathVariable("id") final String id, @RequestBody @Validated ResourceDTO body) {
        body.setId(id);
        return toDto(service.createOrUpdate(fromDto(body)));
    }

    @PutMapping("/{resourceId}/attributes/{attributeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceAttributeDTO saveOrUpdate(@PathVariable("resourceId") final String resourceId,
                                             @PathVariable("attributeId") final String attributeId,
                                             @RequestBody @Validated ResourceAttributeDTO body) {
        body.setId(attributeId);
        return this.toDto(service.addAttribute(resourceId, fromDto(body)));
    }

    @GetMapping("/{resourceId}/attributes")
    @ResponseStatus(HttpStatus.OK)
    public List<ResourceAttributeDTO> getAttributes(@PathVariable("resourceId") final String resourceId) {
        ResourceDefinition resourceDefinition = service.findByExternalCode(resourceId);
        if (CollectionUtils.isEmpty(resourceDefinition.getAttributes())) {
            return Collections.EMPTY_LIST;
        }
        return resourceDefinition.getAttributes().stream().map(this::toDto).collect(Collectors.toList());
    }

    protected ResourceAttributeDTO toDto(ResourceAttributeDefinition definition) {
        ResourceAttributeDTO.ResourceAttributeDTOBuilder builder = ResourceAttributeDTO.builder();
        builder.id(definition.getExternalCode())

                .required(definition.getRequired());
        if (definition.getAttributeDefinition() != null) {
            builder.definitionId(definition.getAttributeDefinition().getId())
                    .type(definition.getAttributeDefinition().getType())
                    .name(definition.getAttributeDefinition().getName())
                    .description(definition.getAttributeDefinition().getDescription());
        }
        return builder.build();
    }

    protected ResourceAttributeDefinition fromDto(ResourceAttributeDTO dto) {
        ResourceAttributeDefinition.ResourceAttributeDefinitionBuilder builder = ResourceAttributeDefinition.builder();
        builder.externalCode(dto.getId())
                .id(dto.getDefinitionId())
                .required(dto.getRequired());
        if (dto.getDefinitionId() != null) {
            builder.attributeDefinition(AttributeDefinition.builder().id(dto.getDefinitionId()).build());
        }
        return builder.build();
    }

    protected ResourceDTO toDto(ResourceDefinition resourceDefinition) {
        ResourceDTO.ResourceDTOBuilder builder = ResourceDTO.builder();
        builder.id(resourceDefinition.getExternalCode())
                .name(resourceDefinition.getName())
                .description(resourceDefinition.getDescription());
        if (!CollectionUtils.isEmpty(resourceDefinition.getAttributes())) {
            builder.attributes(resourceDefinition.getAttributes().stream().map(this::toDto).collect(Collectors.toList()));
        }
        return builder.build();
    }

    protected ResourceDefinition fromDto(ResourceDTO dto) {
        ResourceDefinition.ResourceDefinitionBuilder builder = ResourceDefinition.builder();
        builder.externalCode(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription());
        if (!CollectionUtils.isEmpty(dto.getAttributes())) {
            builder.attributes(dto.getAttributes().stream().map(this::fromDto).collect(Collectors.toList()));
        }
        return builder.build();
    }

}