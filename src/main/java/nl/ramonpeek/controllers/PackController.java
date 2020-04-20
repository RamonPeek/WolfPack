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

    public PackController(IPackManager packManager, IWolfManager wolfManager) {
        this.packManager = packManager;
        this.wolfManager = wolfManager;
    }

    @ApiOperation(value = "Get a pack based on an id")
    @GetMapping("{packId}")
    public ResponseEntity<Pack> getPackById(@PathVariable("packId") int packId) {
        //Check if a pack exists with id = {packId}.
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A pack with id " + packId + " could not be found.");
        return new ResponseEntity<Pack>(pack, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a collection of all packs.")
    @GetMapping()
    public ResponseEntity<List<Pack>> getAllPacks() {
        //Always return a list; even if no packs exist.
        return new ResponseEntity<List<Pack>>(packManager.getAllPacks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a pack.")
    @PostMapping()
    public ResponseEntity<String> createPack(@RequestBody Pack pack) {
        //Validate if the request-body contains a valid Pack-object.
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(pack).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Pack-object.");
        //Check if a pack already exists with id = {pack.getId()}.
        if(packManager.getPackById(pack.getId()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body contains pack id " + pack.getId() + " which is already bound to another pack.");
        Pack createdPack = packManager.createPack(pack);
        //Check if the creation of the pack was successful.
        if(createdPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The pack with id " + pack.getId() + " could not be created due to a server error.");
        return new ResponseEntity<String>("Successfully created a new pack with id " + pack.getId() + ".", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add a wolf to a pack.")
    @PostMapping("{packId}/wolf/{wolfId}")
    public ResponseEntity<String> addWolfToPack(@PathVariable("wolfId") int wolfId, @PathVariable("packId") int packId) {
        //Check if a pack exists with id = {packId}.
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be added to a pack with id " + packId + " as the pack does not exist.");
        //Check if the pack already contains a wolf with id = {wolfId}.
        Wolf wolf = pack.getWolves().stream().filter(w -> w.getId() == wolfId).findFirst().orElse(null);
        if(wolf != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A wolf with id " + wolfId + " already exists in a pack with id " + packId + ".");
        Pack updatedPack = packManager.addWolfToPack(wolfManager.getWolfById(wolfId), pack);
        //Check if the wolf was successfully added to the pack.
        if(updatedPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A wolf with id " + wolfId + " could not be added to a pack with id " + packId + " due to a server error.");
        return new ResponseEntity<String>("Successfully added a wolf with id " + wolfId + " to a pack with id " + packId + ".", HttpStatus.OK);
    }

    @ApiOperation(value = "Remove a wolf from a pack.")
    @DeleteMapping("{packId}/wolf/{wolfId}")
    public ResponseEntity<String> removeWolfFromPack(@PathVariable("wolfId") int wolfId, @PathVariable("packId") int packId) {
        //Check if a pack exists with id = {packId}.
        Pack pack = packManager.getPackById(packId);
        if(pack == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be removed from a pack with id " + packId + " as the pack does not exist.");
        //Check if the pack does not contain a wolf with id = {wolfId}.
        Wolf wolf = pack.getWolves().stream().filter(w -> w.getId() == wolfId).findFirst().orElse(null);
        if(wolf == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be removed from a pack with id " + packId + " as the pack does not contain the wolf.");
        Pack updatedPack = packManager.removeWolfFromPack(wolf, pack);
        //Check if the wolf was successfully removed from the pack.
        if(updatedPack == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A wolf with id " + wolfId + " could not be removed from a pack with id " + packId + " due to a server error.");
        return new ResponseEntity<String>("Successfully removed a wolf with id " + wolfId + " from a pack with id " + packId + ".", HttpStatus.OK);
    }
}
