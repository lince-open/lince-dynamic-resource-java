package work.lince.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.lince.dynamic.model.AttributeDefinition;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Optional;

public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Long> {

}

