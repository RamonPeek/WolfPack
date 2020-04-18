package nl.ramonpeek.dal.memory;

import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;

import java.util.ArrayList;
import java.util.List;

public class PackMemoryContext implements IPackContext {

    private List<Pack> memory = new ArrayList<Pack>();

    public void setMemory(List<Pack> memory) {
        this.memory = memory;
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
