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
        memory.add(wolf);
        return wolf;
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        memory.remove(memory.stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null));
        return wolf;
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        memory.set(memory.indexOf(memory.stream().filter(w -> w.getId() == requestedWolf.getId()).findFirst().orElse(null)), updatedWolf);
        return updatedWolf;
    }
}
