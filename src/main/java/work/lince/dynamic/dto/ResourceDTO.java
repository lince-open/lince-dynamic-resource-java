package work.lince.dynamic.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO {

    @NotNull
    private String id;

    @NotNull
    private String name;

    private String description;

    private List<ResourceAttributeDTO> attributes;

}
