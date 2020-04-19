package nl.ramonpeek.controllers.interfaces;

import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Wolf;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IWolfController {

    /**
     * A method for retrieving a wolf based on an id.
     * @param wolfId the id of the wolf that needs to be retrieved.
     * @return a response entity containing the status code and the found Wolf-object; if no wolf is found this returns a NOT_FOUND response.
     */
    ResponseEntity<Wolf> getWolfById(int wolfId);

    /**
     * A method for retrieving all wolves.
     * @return a response entity containing the status code and a collection of Wolf-objects; if no wolves are found this returns an empty list.
     */
    ResponseEntity<List<Wolf>> getAllWolves();

    /**
     * A method for creating a wolf.
     * @param wolf the Wolf-object that needs to be created.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> createWolf(Wolf wolf);

    /**
     * A method for deleting a wolf.
     * @param wolfId the id of the wolf that needs to be deleted.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> deleteWolf(int wolfId);

    /**
     * A method for updating a wolf.
     * @param wolfId the id of the wolf that needs to be updated.
     * @param updatedWolf the updated Wolf-object.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> updateWolf(int wolfId, Wolf updatedWolf);

    /**
     * A method for updating the location of a wolf.
     * @param wolfId the id of the wolf that needs its location to be updated.
     * @param location the updated Location-object.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> updateLocation(int wolfId, Location location);

}
