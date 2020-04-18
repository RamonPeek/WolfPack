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
}
