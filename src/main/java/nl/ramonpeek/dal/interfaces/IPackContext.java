package nl.ramonpeek.dal.interfaces;

import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;

import java.util.List;

public interface IPackContext {

    /**
     * A method for retrieving a pack based on an id.
     * @param packId the id of the pack that needs to be retrieved.
     * @return the found Pack-object; if no pack is found this returns null.
     */
    Pack getPackById(int packId);

    /**
     * A method for retrieving all packs.
     * @return a collection of Pack-objects; if no packs are found this returns null.
     */
    List<Pack> getAllPacks();

    /**
     * A method for creating a pack.
     * @param pack the Pack-object that needs to be created.
     * @return the created Pack-object; if the pack could not be created this returns null.
     */
    Pack createPack(Pack pack);

    /**
     * A method for adding a wolf to a pack.
     * @param wolf the Wolf-object that needs to be added to a pack.
     * @param pack the Pack-object that a wolf needs to be added to.
     * @return the updated Pack-object containing the newly added wolf; if the wolf could not be added or the packId is invalid this returns null.
     */
    Pack addWolfToPack(Wolf wolf, Pack pack);

    /**
     * A method for removing a wolf from a pack.
     * @param wolf the Wolf-object that needs to be removed from a pack.
     * @param pack the Pack-object that a wolf needs to be removed from.
     * @return the updated Pack-object without the Wolf-object from the request; if the wolf could not be removed or the packId is invalid this returns null.
     */
    Pack removeWolfFromPack(Wolf wolf, Pack pack);

}
