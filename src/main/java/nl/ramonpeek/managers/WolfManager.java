package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IWolfRepo;

import java.util.List;

public class WolfManager implements IWolfManager {

    private IWolfRepo wolfRepo;

    public WolfManager(IWolfRepo wolfRepo) {
        this.wolfRepo = wolfRepo;
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
