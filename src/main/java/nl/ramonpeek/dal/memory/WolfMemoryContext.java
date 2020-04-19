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
        return memory.stream().filter(w -> w.getId() == wolfId).findFirst().orElse(null);
    }

    @Override
    public List<Wolf> getAllWolves() {
        return memory;
    }

    @Override
    public Wolf createWolf(Wolf wolf) {
        if(wolf == null)
            return null;
        memory.add(wolf);
        return wolf;
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        if(wolf == null)
            return null;
        memory.remove(memory.stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null));
        return wolf;
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        if(requestedWolf == null || updatedWolf == null)
            return null;
        memory.set(memory.indexOf(memory.stream().filter(w -> w.getId() == requestedWolf.getId()).findFirst().orElse(null)), updatedWolf);
        return updatedWolf;
    }

    @Override
    public boolean containsWolf(Wolf wolf) {
        if(wolf == null)
            return false;
        return memory.stream().anyMatch(w -> w.getId() == wolf.getId());
    }
}
