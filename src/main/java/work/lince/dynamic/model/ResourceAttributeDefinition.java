package work.lince.dynamic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_RESOURCE_ATTRIBUTE", uniqueConstraints = @UniqueConstraint(columnNames = "EXTERNAL_CODE"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAttributeDefinition {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "EXTERNAL_CODE", length = 50)
    private String externalCode;

    @NotNull
    @Column(name = "REQUIRED")
    private Boolean required;

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID")
    private AttributeDefinition attributeDefinition;

}
