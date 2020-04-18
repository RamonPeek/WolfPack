package nl.ramonpeek.repositories.interfaces;

import nl.ramonpeek.models.Wolf;

import java.util.List;

public interface IWolfRepo {

    /**
     * A method for retrieving a wolf based on an id.
     * @param wolfId the id of the wolf that needs to be retrieved.
     * @return the found Wolf-object; if no wolf is found this returns null.
     */
    Wolf getWolfById(int wolfId);

    /**
     * A method for retrieving all wolves.
     * @return a collection of Wolf-objects; if no wolves are found this returns null.
     */
    List<Wolf> getAllWolves();

    /**
     * A method for creating a wolf.
     * @param wolf the Wolf-object that needs to be created.
     * @return the created Wolf-object; if the wolf could not be created this returns null.
     */
    Wolf createWolf(Wolf wolf);

    /**
     * A method for deleting a wolf.
     * @param wolf the Wolf-object that needs to be deleted.
     * @return the deleted Wolf-object; if the wolf could not be deleted this returns null.
     */
    Wolf deleteWolf(Wolf wolf);

    /**
     * A method for updating a wolf.
     * @param requestedWolf the requested Wolf-object.
     * @param updatedWolf the updated Wolf-object.
     * @return the updated Wolf-object; if the wolfId is invalid this returns null.
     */
    Wolf updateWolf(Wolf requestedWolf, Wolf updatedWolf);

}
