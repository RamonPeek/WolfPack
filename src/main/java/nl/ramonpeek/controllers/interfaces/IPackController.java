package nl.ramonpeek.controllers.interfaces;

import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;

import java.util.List;

public interface IPackController {

    Wolf getPackById(int packId);
    List<Pack> getAllPacks();
    Pack createPack(Pack pack);
    Pack addWolfToPack(Wolf wolf, int packId);
    Pack removeWolfFromPack(Wolf wolf, int packId);

}
