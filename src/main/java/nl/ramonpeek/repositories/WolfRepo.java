package nl.ramonpeek.repositories;

import nl.ramonpeek.dal.interfaces.IWolfContext;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IWolfRepo;

import java.util.List;

public class WolfRepo implements IWolfRepo {

    private IWolfContext wolfContext;

    public WolfRepo(IWolfContext wolfContext) {
        this.wolfContext = wolfContext;
    }

    @Override
    public Wolf getWolfById(int wolfId) {
        return wolfContext.getWolfById(wolfId);
    }

    @Override
    public List<Wolf> getAllWolves() {
        return wolfContext.getAllWolves();
    }

    @Override
    public Wolf createWolf(Wolf wolf) {
        return wolfContext.createWolf(wolf);
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        return wolfContext.deleteWolf(wolf);
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        return wolfContext.updateWolf(requestedWolf, updatedWolf);
    }

    @Override
    public boolean containsWolf(Wolf wolf) {
        return wolfContext.containsWolf(wolf);
    }
}
