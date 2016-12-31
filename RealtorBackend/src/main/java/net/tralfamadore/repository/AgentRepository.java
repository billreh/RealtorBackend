package net.tralfamadore.repository;

import net.tralfamadore.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Bill on 12/30/2016.
 */
@RepositoryRestResource(exported = false)
public interface AgentRepository extends JpaRepository<Agent,Long> {
}
