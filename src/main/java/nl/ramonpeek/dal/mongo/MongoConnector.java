package nl.ramonpeek.dal.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoConnector {

    private static MongoDatabase mongoDatabase;

    /**
     * Method for getting the necessary data to open the connection to the Mongo database.
     * @return a singleton in the form of a MongoDatabase-object.
     */
    public static MongoDatabase getMongoDatabase() {
        if(mongoDatabase == null) {
            MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
            MongoClient mongoClient = new MongoClient(connectionString);
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            mongoDatabase = mongoClient.getDatabase("wolfpackapp").withCodecRegistry(pojoCodecRegistry);
        }
        return mongoDatabase;
    }

}
