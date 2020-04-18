package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pack", produces = "application/json")
public class PackController {

    @Autowired
    private IPackManager packManager;

    @ApiOperation(value = "Get a pack based on an id", response = Pack.class)
    @GetMapping("{packId}")
    public Wolf getPackById(@PathVariable("packId") int packId) {
        return null;
    }

    @ApiOperation(value = "Get a collection of all packs.", response = Pack.class, responseContainer = "List")
    @GetMapping()
    public List<Pack> getAllPacks() {
        return null;
    }

    @ApiOperation(value = "Create a pack.", response = Pack.class)
    @PostMapping()
    public Pack createPack(@RequestBody Pack pack) {
        return null;
    }

    @ApiOperation(value = "Add a wolf to a pack.", response = Pack.class)
    @PutMapping("{packId}/addWolf")
    public Pack addWolfToPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        return null;
    }

    @ApiOperation(value = "Remove a wolf from a pack.", response = Pack.class)
    @PutMapping("{packId}/removeWolf")
    public Pack removeWolfFromPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        return null;
    }

}
