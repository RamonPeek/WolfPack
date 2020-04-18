package nl.ramonpeek.dal.memory;

import nl.ramonpeek.dal.interfaces.IWolfContext;
import nl.ramonpeek.models.Wolf;

import java.util.ArrayList;
import java.util.List;

public class WolfMemoryContext implements IWolfContext {

    private List<Wolf> memory = new ArrayList<Wolf>();

    public void setMemory(List<Wolf> memory) {
        this.memory = memory;
    }

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
}
