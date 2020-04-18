package nl.ramonpeek.controllers.interfaces;

import nl.ramonpeek.models.Wolf;

import java.util.List;

public interface IWolfController {

    Wolf getWolfById(int wolfId);
    List<Wolf> getAllWolves();
    Wolf createWolf(Wolf wolf);
    Wolf deleteWolf(Wolf wolf);
    Wolf updateWolf(int wolfId, Wolf updatedWolf);

}
