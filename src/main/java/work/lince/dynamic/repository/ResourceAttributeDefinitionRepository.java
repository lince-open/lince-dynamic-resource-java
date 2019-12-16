package work.lince.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.dynamic.model.ResourceAttributeDefinition;
import work.lince.dynamic.model.ResourceDefinition;

import java.util.Optional;

public interface ResourceAttributeDefinitionRepository extends JpaRepository<ResourceAttributeDefinition, Long> {

    Optional<ResourceDefinition> findByExternalCode(String externalCode);

}

