package work.lince.dynamic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "TB_RESOURCE", uniqueConstraints = @UniqueConstraint(columnNames = "EXTERNAL_CODE"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDefinition {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "EXTERNAL_CODE", length = 50)
    private String externalCode;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION", length = 250)
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESOURCE_ID")
    private List<ResourceAttributeDefinition> attributes;

}
