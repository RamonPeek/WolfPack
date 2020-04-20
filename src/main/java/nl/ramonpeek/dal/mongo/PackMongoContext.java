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

    private MongoCollection<Pack> packsCollection = MongoConnector.getMongoDatabase().getCollection("packs", Pack.class);

    @Override
    public Pack getPackById(int packId) {
        List<Pack> foundPacks = new ArrayList<Pack>();
        packsCollection.find(new Document() {{
            put("_id", packId);
        }}).limit(1).into(foundPacks);
        if(foundPacks.size() <= 0)
            return null;
        return foundPacks.get(0);
    }

    @Override
    public List<Pack> getAllPacks() {
        List<Pack> foundPacks = new ArrayList<Pack>();
        packsCollection.find().into(foundPacks);
        return foundPacks;
    }

    @Override
    public Pack createPack(Pack pack) {
        packsCollection.insertOne(pack);
        return getPackById(pack.getId());
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        pack.getWolves().add(wolf);
        return packsCollection.findOneAndReplace(new Document() {{
            put("_id", pack.getId());
        }}, pack);
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        Wolf wolfToRemove = pack.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findAny().orElse(null);
        if(wolfToRemove == null)
            return null;
        pack.getWolves().remove(wolfToRemove);
        return packsCollection.findOneAndReplace(new Document() {{
            put("_id", pack.getId());
        }}, pack);
    }

    @Override
    public boolean containsPack(Pack pack) {
        return getPackById(pack.getId()) != null;
    }
}
