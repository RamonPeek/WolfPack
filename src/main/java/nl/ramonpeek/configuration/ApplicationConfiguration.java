package nl.ramonpeek.configuration;

import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.repositories.PackRepo;
import nl.ramonpeek.repositories.WolfRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public IWolfManager wolfManager() {
        return new WolfManager(new WolfRepo(new WolfMemoryContext()));
    }

    @Bean
    public IPackManager packManager() {
        return new PackManager(new PackRepo(new PackMemoryContext()));
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

}
