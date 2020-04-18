package nl.ramonpeek.controllers;

import nl.ramonpeek.managers.interfaces.IPackManager;
import nl.ramonpeek.managers.interfaces.IWolfManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WolfController {

    @Autowired
    private IWolfManager wolfManager;

}
