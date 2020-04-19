package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.controllers.interfaces.IWolfController;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/wolf", produces = "application/json")
public class WolfController implements IWolfController {

    @Autowired
    private IWolfManager wolfManager;

    @ApiOperation(value = "Get a wolf based on an id", response = Wolf.class)
    @GetMapping("{wolfId}")
    public Wolf getWolfById(@PathVariable("wolfId") int wolfId) {
        return wolfManager.getWolfById(wolfId);
    }

    @ApiOperation(value = "Get a collection of all wolves.", response = Wolf.class, responseContainer = "List")
    @GetMapping()
    public List<Wolf> getAllWolves() {
        return wolfManager.getAllWolves();
    }

    @ApiOperation(value = "Create a wolf.", response = Wolf.class)
    @PostMapping()
    public Wolf createWolf(@RequestBody Wolf wolf) {
        return wolfManager.createWolf(wolf);
    }

    @ApiOperation(value = "Delete a wolf.", response = Wolf.class)
    @DeleteMapping("{wolfId}")
    public Wolf deleteWolf(@PathVariable("wolfId") int wolfId) {
        Wolf wolf = wolfManager.getWolfById(wolfId);
        return wolfManager.deleteWolf(wolf);
    }

    @ApiOperation(value = "Update a wolf.", response = Wolf.class)
    @PutMapping("{wolfId}")
    public Wolf updateWolf(@PathVariable("wolfId") int wolfId, @RequestBody Wolf updatedWolf) {
        Wolf wolf = wolfManager.getWolfById(wolfId);
        return wolfManager.updateWolf(wolf, updatedWolf);
    }

}
