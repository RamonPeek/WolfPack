package nl.ramonpeek.dal.mongo;

import nl.ramonpeek.dal.interfaces.IWolfContext;
import nl.ramonpeek.models.Wolf;

import java.util.List;

public class WolfMongoContext implements IWolfContext {

    @Override
    public Wolf getWolfById(int wolfId) {
        return null;
    }

    @Override
    public List<Wolf> getAllWolves() {
        return null;
    }

    @Override
    public Wolf createWolf(Wolf wolf) {
        return null;
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        return null;
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        return null;
    }

    @Override
    public boolean containsWolf(Wolf wolf) {
        return false;
    }
}
