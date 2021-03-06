package nl.ramonpeek.repositories;

import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IPackRepo;
import java.util.List;

public class PackRepo implements IPackRepo {

    private IPackContext packContext;

    public PackRepo(IPackContext packContext) {
        this.packContext = packContext;
    }

    @Override
    public Pack getPackById(int packId) {
        return packContext.getPackById(packId);
    }

    @Override
    public List<Pack> getAllPacks() {
        return packContext.getAllPacks();
    }

    @Override
    public Pack createPack(Pack pack) {
        if(pack == null)
            return null;
        return packContext.createPack(pack);
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        if(wolf == null || pack == null)
            return null;
        return packContext.addWolfToPack(wolf, pack);
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        if(wolf == null || pack == null)
            return null;
        return packContext.removeWolfFromPack(wolf, pack);
    }

    @Override
    public boolean containsPack(Pack pack) {
        if(pack == null)
            return false;
        return packContext.containsPack(pack);
    }
}
