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
        return wolfRepo.getWolfById(wolfId);
    }

    @Override
    public List<Wolf> getAllWolves() {
        return wolfRepo.getAllWolves();
    }

    @Override
    public Wolf createWolf(Wolf wolf) {
        return wolfRepo.createWolf(wolf);
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        return wolfRepo.deleteWolf(wolf);
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        return wolfRepo.updateWolf(requestedWolf, updatedWolf);
    }
}
