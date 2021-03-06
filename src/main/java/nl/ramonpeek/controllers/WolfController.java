package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.controllers.interfaces.IWolfController;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Location;
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

    @ApiOperation(value = "Get a wolf based on an id.")
    @GetMapping("{wolfId}")
    public ResponseEntity<Wolf> getWolfById(@PathVariable("wolfId") int wolfId) {
        //Check if a wolf exists with id = {wolfId}.
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A wolf with id " + wolfId + " could not be found.");

        return new ResponseEntity<Wolf>(wolf, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a collection of all wolves.")
    @GetMapping()
    public ResponseEntity<List<Wolf>> getAllWolves() {
        //Always return a list; even if no wolves exist.
        return new ResponseEntity<List<Wolf>>(wolfManager.getAllWolves(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a wolf.")
    @PostMapping()
    public ResponseEntity<String> createWolf(@RequestBody Wolf wolf) {
        //Validate if the request-body contains a valid Wolf-object.
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(wolf).isEmpty())
            return new ResponseEntity<String>("The provided request-body does not contain a valid Wolf-object.", HttpStatus.BAD_REQUEST);

        //Check if a wolf already exists with id = {wolf.getId()}.
        if(wolfManager.getWolfById(wolf.getId()) != null)
            return new ResponseEntity<String>("The provided request-body contains wolf id " + wolf.getId() + " which is already bound to another wolf.", HttpStatus.BAD_REQUEST);

        //Create the wolf.
        Wolf createdWolf = wolfManager.createWolf(wolf);

        //Check if the creation of the wolf was successful.
        if(createdWolf == null)
            return new ResponseEntity<String>("The wolf with id " + wolf.getId() + " could not be created due to a server error.", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<String>("Successfully created a new wolf with id " + wolf.getId() + ".", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a wolf.")
    @DeleteMapping("{wolfId}")
    public ResponseEntity<String> deleteWolf(@PathVariable("wolfId") int wolfId) {
        //Check if a wolf exists with id = {wolfId}.
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            return new ResponseEntity<String>("A wolf with id " + wolfId + " could not be deleted as it could not be found.", HttpStatus.NOT_FOUND);

        //Delete the wolf.
        Wolf deletedWolf = wolfManager.deleteWolf(wolf);

        //Check if the deletion of the wolf was successful.
        if(deletedWolf == null)
            return new ResponseEntity<String>("The wolf with id " + wolfId + " could not be deleted due to a server error.", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<String>("Successfully deleted a wolf with id " + wolf.getId() + ".", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a wolf.")
    @PutMapping("{wolfId}")
    public ResponseEntity<String> updateWolf(@PathVariable("wolfId") int wolfId, @RequestBody Wolf updatedWolf) {
        //Validate if the request-body contains a valid Wolf-object.
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(updatedWolf).isEmpty())
            return new ResponseEntity<String>("The provided request-body does not contain a valid Wolf-object.", HttpStatus.BAD_REQUEST);

        //Check if a wolf exists with id = {wolfId}.
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            return new ResponseEntity<String>("A wolf with id " + wolfId + " could not be updated as it could not be found.", HttpStatus.NOT_FOUND);

        //Check if the updated wolf is the same as the requested wolf.
        if(updatedWolf.getId() != wolfId)
            return new ResponseEntity<String>("A wolf with id " + wolfId + " could not be updated as the wolfId in the parameters and request body do not match.", HttpStatus.BAD_REQUEST);

        //Update the wolf.
        Wolf newWolf = wolfManager.updateWolf(wolf, updatedWolf);

        //Check if the wolf was successfully updated.
        if(newWolf == null)
            return new ResponseEntity<String>("The wolf with id " + wolfId + " could not be updated due to a server error.", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<String>("Successfully updated a wolf with id " + wolf.getId() + ".", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a wolf's location.")
    @PutMapping("{wolfId}/location")
    public ResponseEntity<String> updateLocation(@PathVariable("wolfId") int wolfId, @RequestBody Location location) {
        //Validate if the request-body contains a valid Location-object.
        Validator validator = validatorFactory.getValidator();
        if(!validator.validate(location).isEmpty())
            return new ResponseEntity<String>("The provided request-body does not contain a valid Location-object.", HttpStatus.BAD_REQUEST);

        //Check if a wolf exists with id = {wolfId}.
        Wolf wolf = wolfManager.getWolfById(wolfId);
        if(wolf == null)
            return new ResponseEntity<String>("A wolf with id " + wolfId + " could not be updated as it could not be found.", HttpStatus.NOT_FOUND);

        //Update the location of the wolf.
        wolf.setLocation(location);

        //Update the wolf's location.
        Wolf newWolf = wolfManager.updateWolf(wolf, wolf);

        //Check if the location of the wolf was successfully updated.
        if(newWolf == null)
            return new ResponseEntity<String>("The wolf with id " + wolfId + " could not be updated due to a server error.", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<String>("Successfully updated the location of a wolf with id " + wolf.getId() + " to: (" + location.getLatitude() + "," + location.getLongitude() + ").", HttpStatus.OK);
    }

}
