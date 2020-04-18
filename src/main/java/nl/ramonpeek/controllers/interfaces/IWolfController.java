package nl.ramonpeek.controllers.interfaces;

import nl.ramonpeek.models.Wolf;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IWolfController {

    Wolf getWolfById(int wolfId);
    List<Wolf> getAllWolves();
    Wolf createWolf(Wolf wolf);
    Wolf deleteWolf(Wolf wolf);
    Wolf updateWolf(int wolfId, Wolf updatedWolf);

}
