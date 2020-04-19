package nl.ramonpeek.tests.endpoints;

import nl.ramonpeek.controllers.PackController;
import nl.ramonpeek.controllers.WolfController;
import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import nl.ramonpeek.repositories.PackRepo;
import nl.ramonpeek.repositories.WolfRepo;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Date;

public class WolfEndpointTests {

    private WolfController wolfController = new WolfController(new WolfManager(new WolfRepo(new WolfMemoryContext())));

    @BeforeEach
    public void resetTestEnvironment() {
        WolfMemoryContext wolfContext = new WolfMemoryContext();
        wolfContext.setMemory(new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});
        wolfController = new WolfController(new WolfManager(new WolfRepo(wolfContext)));
    }

}
