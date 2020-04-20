package nl.ramonpeek.dal.mongo;

import com.mongodb.client.MongoCollection;
import nl.ramonpeek.dal.interfaces.IWolfContext;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class WolfMongoContext implements IWolfContext {

    private MongoCollection<Wolf> wolvesCollection = MongoConnector.getMongoDatabase().getCollection("wolves", Wolf.class);

    @Override
    public Wolf getWolfById(int wolfId) {
        List<Wolf> foundWolves = new ArrayList<Wolf>();
        wolvesCollection.find(new Document() {{
            put("_id", wolfId);
        }}).limit(1).into(foundWolves);
        if(foundWolves.size() <= 0)
            return null;
        return foundWolves.get(0);
    }

    @Override
    public List<Wolf> getAllWolves() {
        List<Wolf> foundPacks = new ArrayList<Wolf>();
        wolvesCollection.find().into(foundPacks);
        return foundPacks;
    }

    @Override
    public Wolf createWolf(Wolf wolf) {
        wolvesCollection.insertOne(wolf);
        return getWolfById(wolf.getId());
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        return wolvesCollection.findOneAndDelete(new Document() {{
           put("_id", wolf.getId());
        }});
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        return wolvesCollection.findOneAndReplace(new Document() {{
            put("_id", requestedWolf.getId());
        }}, updatedWolf);
    }

    @Override
    public boolean containsWolf(Wolf wolf) {
        return getWolfById(wolf.getId()) != null;
    }
}
