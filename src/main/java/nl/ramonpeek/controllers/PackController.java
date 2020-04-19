package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.controllers.interfaces.IPackController;
import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

@RestController
@RequestMapping(value = "/pack")
public class PackController implements IPackController {

    @Autowired
    private IPackManager packManager;

    @Autowired
    private IWolfManager wolfManager;

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @ApiOperation(value = "Get a pack based on an id", response = Pack.class)
    @GetMapping("{packId}")
    public ResponseEntity<Pack> getPackById(@PathVariable("packId") int packId) {
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A pack with id " + packId + " could not be found.");
        return new ResponseEntity<Pack>(pack, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a collection of all packs.", response = Pack.class, responseContainer = "List")
    @GetMapping()
    public ResponseEntity<List<Pack>> getAllPacks() {
        return new ResponseEntity<List<Pack>>(packManager.getAllPacks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a pack.", response = Pack.class)
    @PostMapping()
    public ResponseEntity<String> createPack(@RequestBody Pack pack) {
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(pack).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Pack-object.");
        if(packManager.containsPack(pack))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body contains pack id " + pack.getId() + " which is already bound to another pack.");
        Pack createdPack = packManager.createPack(pack);
        if(createdPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The pack with id " + pack.getId() + " could not be created due to a server error.");
        return new ResponseEntity<String>("Successfully created a new pack with id " + pack.getId() + ".", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add a wolf to a pack.", response = Pack.class)
    @PutMapping("{packId}/addWolf")
    public ResponseEntity<String> addWolfToPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(wolf).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Wolf-object.");
        if(wolfManager.getWolfById(wolf.getId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolf.getId() + " could not be added to a pack with id " + packId + " as the wolf does not exist.");
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolf.getId() + " could not be added to a pack with id " + packId + " as the pack does not exist.");
        if(pack.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A wolf with id " + wolf.getId() + " already exists in a pack with id " + packId + ".");
        Pack updatedPack = packManager.addWolfToPack(wolf, pack);
        if(updatedPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A wolf with id " + wolf.getId() + " could not be added to a pack with id " + packId + " due to a server error.");
        return new ResponseEntity<String>("Successfully added a wolf with id " + wolf.getId() + " to a pack with id " + pack.getId() + ".", HttpStatus.OK);
    }

    @ApiOperation(value = "Remove a wolf from a pack.", response = Pack.class)
    @PutMapping("{packId}/removeWolf")
    public ResponseEntity<String> removeWolfFromPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(wolf).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Wolf-object.");
        if(wolfManager.getWolfById(wolf.getId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolf.getId() + " could not be removed from a pack with id " + packId + " as the wolf does not exist.");
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolf.getId() + " could not be removed from a pack with id " + packId + " as the pack does not exist.");
        if(pack.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A wolf with id " + wolf.getId() + " does not exist in a pack with id " + packId + ".");
        Pack updatedPack = packManager.addWolfToPack(wolf, pack);
        if(updatedPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A wolf with id " + wolf.getId() + " could not be removed from a pack with id " + packId + " due to a server error.");
        return new ResponseEntity<String>("Successfully removed a wolf with id " + wolf.getId() + " from a pack with id " + pack.getId() + ".", HttpStatus.OK);
    }

}
