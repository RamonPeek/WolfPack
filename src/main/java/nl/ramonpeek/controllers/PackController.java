package nl.ramonpeek.controllers;

import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.controllers.interfaces.IPackController;
import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pack", produces = "application/json")
public class PackController implements IPackController {

    @Autowired
    private IPackManager packManager;

    @ApiOperation(value = "Get a pack based on an id", response = Pack.class)
    @GetMapping("{packId}")
    public ResponseEntity<Pack> getPackById(@PathVariable("packId") int packId) {
        //return packManager.getPackById(packId);
        return null;
    }

    @ApiOperation(value = "Get a collection of all packs.", response = Pack.class, responseContainer = "List")
    @GetMapping()
    public ResponseEntity<List<Pack>> getAllPacks() {
        //return packManager.getAllPacks();
        return null;
    }

    @ApiOperation(value = "Create a pack.", response = Pack.class)
    @PostMapping()
    public ResponseEntity<String> createPack(@RequestBody Pack pack) {
        //return packManager.createPack(pack);
        return null;
    }

    @ApiOperation(value = "Add a wolf to a pack.", response = Pack.class)
    @PutMapping("{packId}/addWolf")
    public ResponseEntity<String> addWolfToPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        Pack pack = packManager.getPackById(packId);
        //return packManager.addWolfToPack(wolf, pack);
        return null;
    }

    @ApiOperation(value = "Remove a wolf from a pack.", response = Pack.class)
    @PutMapping("{packId}/removeWolf")
    public ResponseEntity<String> removeWolfFromPack(@RequestBody Wolf wolf, @PathVariable("packId") int packId) {
        Pack pack = packManager.getPackById(packId);
        //return packManager.removeWolfFromPack(wolf, pack);
        return null;
    }

}
