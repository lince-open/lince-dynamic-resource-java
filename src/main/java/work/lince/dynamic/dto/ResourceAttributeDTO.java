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

    @NotNull
    private String id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private AttributeType type;

    private Long definitionId;

    private Boolean required;
}
