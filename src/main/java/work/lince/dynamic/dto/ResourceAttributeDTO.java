package work.lince.dynamic.dto;

import lombok.*;
import work.lince.dynamic.model.AttributeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAttributeDTO {

    private String id;

    private String name;

    private String description;

    private AttributeType type;

    @NotNull
    private Long definitionId;

    private Boolean required;
}
