package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IPackRepo;
import javax.validation.Validation;
import javax.validation.Validator;
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
        Validator validator = validatorFactory.getValidator();
        if(pack != null)
            if(validator.validate(pack).isEmpty())
                if(!packRepo.containsPack(pack))
                    return packRepo.createPack(pack);
        return null;
    }

    @Override
    public Pack addWolfToPack(Wolf wolf, Pack pack) {
        Validator validator = validatorFactory.getValidator();
        if(wolf != null && pack != null)
            if(validator.validate(wolf).isEmpty() && validator.validate(pack).isEmpty())
                if(containsPack(pack) && pack.getWolves().stream().noneMatch(w -> w.getId() == wolf.getId()))
                    return packRepo.addWolfToPack(wolf, pack);
        return null;
    }

    @Override
    public Pack removeWolfFromPack(Wolf wolf, Pack pack) {
        Validator validator = validatorFactory.getValidator();
        if(wolf != null && pack != null)
            if(validator.validate(wolf).isEmpty() && validator.validate(pack).isEmpty())
                if(containsPack(pack) && pack.getWolves().stream().anyMatch(w -> w.getId() == wolf.getId()))
                    return packRepo.removeWolfFromPack(wolf, pack);
        return null;
    }

    @Override
    public boolean containsPack(Pack pack) {
        if(pack == null)
            return false;
        return packRepo.containsPack(pack);
    }
}
