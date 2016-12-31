package net.tralfamadore;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by Bill on 12/30/2016.
 */
@Component
@Configuration
@EnableJpaRepositories(basePackages = "net.tralfamadore.repository")
public class RepositoryConfig {
}
