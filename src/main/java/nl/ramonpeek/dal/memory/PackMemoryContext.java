package nl.ramonpeek.dal.memory;

import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.models.Pack;

import java.util.ArrayList;
import java.util.List;

public class PackMemoryContext implements IPackContext {

    private List<Pack> memory = new ArrayList<Pack>();

    public void setMemory(List<Pack> memory) {
        this.memory = memory;
    }
}
