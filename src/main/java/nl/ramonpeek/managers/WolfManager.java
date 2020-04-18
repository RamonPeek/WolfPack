package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.repositories.interfaces.IWolfRepo;

public class WolfManager implements IWolfManager {

    private IWolfRepo wolfRepo;

    public WolfManager(IWolfRepo wolfRepo) {
        this.wolfRepo = wolfRepo;
    }

}
