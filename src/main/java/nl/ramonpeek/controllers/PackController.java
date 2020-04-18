package nl.ramonpeek.controllers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackController {

    @Autowired
    private IPackManager packManager;

}
