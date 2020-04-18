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
