package nl.ramonpeek.dal.mongo;

import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;

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
}
