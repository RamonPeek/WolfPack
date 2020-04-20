package nl.ramonpeek.managers;

import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.interfaces.IWolfRepo;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

public class WolfManager implements IWolfManager {

    private IWolfRepo wolfRepo;

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

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
        Validator validator = validatorFactory.getValidator();
        if(wolf == null || !validator.validate(wolf).isEmpty() || containsWolf(wolf))
            return null;
        return wolfRepo.createWolf(wolf);
    }

    @Override
    public Wolf deleteWolf(Wolf wolf) {
        Validator validator = validatorFactory.getValidator();
        if(wolf == null || !validator.validate(wolf).isEmpty() || !containsWolf(wolf))
            return null;
        return wolfRepo.deleteWolf(wolf);
    }

    @Override
    public Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf) {
        Validator validator = validatorFactory.getValidator();
        if(requestedWolf == null || updatedWolf == null || !validator.validate(requestedWolf).isEmpty() || !validator.validate(updatedWolf).isEmpty() || !containsWolf(requestedWolf))
            return null;
        return wolfRepo.updateWolf(requestedWolf, updatedWolf);
    }

    @Override
    public boolean containsWolf(Wolf wolf) {
        if(wolf == null)
            return false;
        return wolfRepo.containsWolf(wolf);
    }
}
