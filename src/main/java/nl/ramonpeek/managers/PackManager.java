package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IPackRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.List;

public class PackManager implements IPackManager {

    private IPackRepo packRepo;

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public PackManager(IPackRepo packRepo) {
        this.packRepo = packRepo;
    }

    @Override
    public Pack getPackById(int packId) {
        return packRepo.getPackById(packId);
    }

    @Override
    public List<Pack> getAllPacks() {
        return packRepo.getAllPacks();
    }

    @Override
    public Pack createPack(Pack pack) {
        return packRepo.createPack(pack);
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        return packRepo.addWolfToPack(wolf, pack);
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        return packRepo.removeWolfFromPack(wolf, pack);
    }

    @Override
    public boolean containsPack(Pack pack) {
        return packRepo.containsPack(pack);
    }
}
