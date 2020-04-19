package nl.ramonpeek.configuration;

import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.repositories.PackRepo;
import nl.ramonpeek.repositories.WolfRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PackManager packManager() {
        return new PackManager(new PackRepo(new PackMemoryContext()));
    }

    @Bean
    public WolfManager wolfManager() {
        return new WolfManager(new WolfRepo(new WolfMemoryContext()));
    }

}
