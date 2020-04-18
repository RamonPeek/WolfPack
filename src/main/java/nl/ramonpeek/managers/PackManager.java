package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IPackRepo;

import java.util.List;

public class PackManager implements IPackManager {

    private IPackRepo packRepo;

    public PackManager(IPackRepo packRepo) {
        this.packRepo = packRepo;
    }

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
