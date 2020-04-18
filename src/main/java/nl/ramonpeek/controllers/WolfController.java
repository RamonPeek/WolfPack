package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/wolf", produces = "application/json")
public class WolfController {

    @Autowired
    private IWolfManager wolfManager;

    @ApiOperation(value = "Get a collection of all wolves.", response = Wolf.class, responseContainer = "List")
    @GetMapping()
    public List<Wolf> getAllWolves() {
        return null;
    }

    @ApiOperation(value = "Create a wolf.", response = Wolf.class)
    @PostMapping()
    public Wolf createWolf(@RequestBody Wolf wolf) {
        return null;
    }

    @ApiOperation(value = "Delete a wolf.", response = Wolf.class)
    @DeleteMapping()
    public Wolf deleteWolf(@RequestBody Wolf wolf) {
        return null;
    }

    @ApiOperation(value = "Update a wolf.", response = Wolf.class)
    @PutMapping("{wolfId}")
    public Wolf updateWolf(@PathVariable("wolfId") int wolfId, @RequestBody Wolf updatedWolf) {
        return null;
    }

}
