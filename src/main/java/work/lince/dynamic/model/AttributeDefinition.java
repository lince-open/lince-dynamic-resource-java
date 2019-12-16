package work.lince.dynamic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_ATTRIBUTE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDefinition {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @NotNull
    @Column(name = "TYPE",length = 50)
    @Enumerated(EnumType.STRING)
    private AttributeType type;

}
