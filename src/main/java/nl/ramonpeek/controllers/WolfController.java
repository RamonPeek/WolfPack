package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.controllers.interfaces.IWolfController;
import nl.ramonpeek.managers.interfaces.IWolfManager;
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
@RequestMapping(value = "/wolf")
public class WolfController implements IWolfController {

    @Autowired
    private IWolfManager wolfManager;

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @ApiOperation(value = "Get a wolf based on an id", response = Wolf.class)
    @GetMapping("{wolfId}")
    public ResponseEntity<Wolf> getWolfById(@PathVariable("wolfId") int wolfId) {
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be found.");
        return new ResponseEntity<Wolf>(wolf, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a collection of all wolves.", response = Wolf.class, responseContainer = "List")
    @GetMapping()
    public ResponseEntity<List<Wolf>> getAllWolves() {
        return new ResponseEntity<List<Wolf>>(wolfManager.getAllWolves(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a wolf.", response = Wolf.class)
    @PostMapping()
    public ResponseEntity<String> createWolf(@RequestBody Wolf wolf) {
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(wolf).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Wolf-object.");
        if(wolfManager.getWolfById(wolf.getId()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body contains wolf id " + wolf.getId() + " which is already bound to another wolf.");
        Wolf createdWolf = wolfManager.createWolf(wolf);
        if(createdWolf == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The wolf with id " + wolf.getId() + " could not be created due to a server error.");
        return new ResponseEntity<String>("Successfully created a new wolf with id " + wolf.getId() + ".", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a wolf.", response = Wolf.class)
    @DeleteMapping("{wolfId}")
    public ResponseEntity<String> deleteWolf(@PathVariable("wolfId") int wolfId) {
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be deleted as it could not be found.");
        Wolf deletedWolf = wolfManager.deleteWolf(wolf);
        if(deletedWolf == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The wolf with id " + wolfId + " could not be deleted due to a server error.");
        return new ResponseEntity<String>("Successfully deleted a wolf with id " + wolf.getId() + ".", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a wolf.", response = Wolf.class)
    @PutMapping("{wolfId}")
    public ResponseEntity<String> updateWolf(@PathVariable("wolfId") int wolfId, @RequestBody Wolf updatedWolf) {
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(updatedWolf).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided request-body does not contain a valid Wolf-object.");
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolfManager.getWolfById(wolf.getId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be updated as it could not be found.");
        Wolf newWolf = wolfManager.updateWolf(wolf, updatedWolf);
        if(newWolf == null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The wolf with id " + wolfId + " could not be updated due to a server error.");
        return new ResponseEntity<String>("Successfully updated a wolf with id " + wolf.getId() + ".", HttpStatus.OK);
    }

}
