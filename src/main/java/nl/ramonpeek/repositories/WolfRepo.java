package nl.ramonpeek.repositories;

import nl.ramonpeek.dal.interfaces.IWolfContext;
import nl.ramonpeek.repositories.interfaces.IWolfRepo;

public class WolfRepo implements IWolfRepo {

    private IWolfContext wolfContext;

    public WolfRepo(IWolfContext wolfContext) {
        this.wolfContext = wolfContext;
    }

}
