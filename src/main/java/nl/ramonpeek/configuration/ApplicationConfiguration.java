package nl.ramonpeek.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.dal.mongo.PackMongoContext;
import nl.ramonpeek.dal.mongo.WolfMongoContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.repositories.PackRepo;
import nl.ramonpeek.repositories.WolfRepo;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    /**
     * A method for creating a bean which can be used as a singleton.
     * @return A method which returns a singleton for the PackManager which is accessible via @Autowired.
     */
    @Bean
    public PackManager packManager() {
        return new PackManager(new PackRepo(new PackMemoryContext()));
    }

    /**
     * A method for creating a bean which can be used as a singleton.
     * @return A method which returns a singleton for the PackManager which is accessible via @Autowired.
     */
    @Bean
    public WolfManager wolfManager() {
        return new WolfManager(new WolfRepo(new WolfMemoryContext()));
    }

}
