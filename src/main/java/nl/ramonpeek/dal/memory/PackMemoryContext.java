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
        return memory.stream().filter(p -> p.getId() == packId).findFirst().orElse(null);
    }

    @Override
    public List<Pack> getAllPacks() {
        return memory;
    }

    @Override
    public Pack createPack(Pack pack) {
        if(pack == null)
            return null;
        memory.add(pack);
        return pack;
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        if(wolf == null || pack == null)
            return null;
        Pack foundPack = memory.stream().filter(p -> p.getId() == pack.getId()).findFirst().orElse(null);
        if(foundPack == null)
            return null;
        foundPack.getWolves().add(wolf);
        memory.set(memory.indexOf(foundPack), foundPack);
        return foundPack;
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        if(wolf == null || pack == null)
            return null;
        Pack foundPack = memory.stream().filter(p -> p.getId() == pack.getId()).findFirst().orElse(null);
        if(foundPack == null)
            return null;
        foundPack.getWolves().remove(foundPack.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null));
        memory.set(memory.indexOf(foundPack), foundPack);
        return foundPack;
    }

    @Override
    public boolean containsPack(Pack pack) {
        if(pack == null)
            return false;
        return memory.stream().anyMatch(p -> p.getId() == pack.getId());
    }
}
