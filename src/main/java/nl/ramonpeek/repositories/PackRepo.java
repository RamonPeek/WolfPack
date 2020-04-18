package nl.ramonpeek.repositories;

import nl.ramonpeek.dal.interfaces.IPackContext;
import nl.ramonpeek.repositories.interfaces.IPackRepo;

public class PackRepo implements IPackRepo {

    private IPackContext packContext;

    public PackRepo(IPackContext packContext) {
        this.packContext = packContext;
    }

}
