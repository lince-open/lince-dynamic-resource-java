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
        return toDto(service.findAll());
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceDTO createOrUpdate(@PathVariable("id") final String id, @RequestBody @Validated ResourceDTO body) {
        body.setId(id);
        return toDto(service.createOrUpdate(fromDto(body)));
    }

    @PostMapping("/{resourceId}/attributes/{attributeId}")
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
        return attributesToDto(service.findByExternalCode(resourceId).getAttributes());
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

    protected List<ResourceAttributeDTO> attributesToDto(List<ResourceAttributeDefinition> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
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
        return ResourceDTO.builder()
                .build();
    }

    protected List<ResourceDTO> toDto(List<ResourceDefinition> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    protected ResourceDefinition fromDto(ResourceDTO dto) {
        return ResourceDefinition.builder()
                .build();
    }

}