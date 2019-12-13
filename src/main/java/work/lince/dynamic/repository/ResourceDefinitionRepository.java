package work.lince.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.dynamic.model.AttributeDefinition;
import work.lince.dynamic.model.ResourceDefinition;

import java.util.List;
import java.util.Optional;

public interface ResourceDefinitionRepository extends JpaRepository<ResourceDefinition, Long> {

    Optional<ResourceDefinition> findByExternalCode(String externalCode);

}

