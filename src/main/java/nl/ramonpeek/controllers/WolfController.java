package nl.ramonpeek.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/wolf", produces = "application/json")
public class WolfController {

    @Autowired
    private IWolfManager wolfManager;

    @ApiOperation(value = "", response = Wolf.class, responseContainer = "List")
    @GetMapping()
    public List<Wolf> getAllWolves() {
        return null;
    }

    @ApiOperation(value = "", response = Wolf.class)
    @PostMapping()
    public Wolf createWolf(Wolf wolf) {
        return null;
    }

    @ApiOperation(value = "", response = Wolf.class)
    @DeleteMapping()
    public Wolf deleteWolf(Wolf wolf) {
        return null;
    }

    @ApiOperation(value = "", response = Wolf.class)
    @PutMapping
    public Wolf updateWolf(int wolfId, Wolf updatedWolf) {
        return null;
    }

}
