package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.repositories.interfaces.IPackRepo;

public class PackManager implements IPackManager {

    private IPackRepo packRepo;

    public PackManager(IPackRepo packRepo) {
        this.packRepo = packRepo;
    }

}
