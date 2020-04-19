package nl.ramonpeek.controllers.interfaces;

import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPackController {

    /**
     * A method for retrieving a pack based on an id.
     * @param packId the id of the pack that needs to be retrieved.
     * @return a response entity containing the status code and the found Pack-object; if no pack is found this returns null.
     */
    ResponseEntity<Pack> getPackById(int packId);

    /**
     * A method for retrieving all packs.
     * @return a response entity containing the status code and a collection of Pack-objects; if no packs are found this returns an empty list.
     */
    ResponseEntity<List<Pack>> getAllPacks();

    /**
     * A method for creating a pack.
     * @param pack the Pack-object that needs to be created.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> createPack(Pack pack);

    /**
     * A method for adding a wolf to a pack.
     * @param wolf the Wolf-object that needs to be added to a pack.
     * @param packId the id of the pack that a wolf needs to be added to.
     * @return the updated Pack-object containing the newly added wolf; if the wolf could not be added or the packId is invalid this returns null.
     */
    ResponseEntity<String> addWolfToPack(Wolf wolf, int packId);

    /**
     * A method for removing a wolf from a pack.
     * @param wolf the Wolf-object that needs to be removed from a pack.
     * @param packId the id of the pack that a wolf needs to be removed from.
     * @return a response entity containing the status code and body.
     */
    ResponseEntity<String> removeWolfFromPack(Wolf wolf, int packId);

}
