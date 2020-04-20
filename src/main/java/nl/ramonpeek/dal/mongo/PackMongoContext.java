package nl.ramonpeek.dal.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import nl.ramonpeek.configuration.ApplicationConfiguration;
import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PackMongoContext implements IPackContext {

    @Override
    public Pack getPackById(int packId) {
        return null;
    }

    @Override
    public List<Pack> getAllPacks() {
        return null;
    }

    @Override
    public Pack createPack(Pack pack) {
        return null;
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        return null;
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        return null;
    }

    @Override
    public boolean containsPack(Pack pack) {
        return false;
    }
}
