package nl.ramonpeek.tests;

import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import nl.ramonpeek.repositories.PackRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestPackManager {

    private PackManager packManager;

    @Before
    public void resetTestEnvironment() {
        PackMemoryContext context = new PackMemoryContext();
        context.setMemory(new ArrayList<Pack>() {{
            add(new Pack(0, "AlphaPack", new ArrayList<Wolf>() {{
                add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
                add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
            }}));
            add(new Pack(1, "BetaPack", new ArrayList<Wolf>() {{
                add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            }}));
        }});
        this.packManager = new PackManager(new PackRepo(context));
    }

    @Test
    public void testGetPackByExistingId() {
        //Arrange
        int packId = 0;

        //Act
        Pack result = packManager.getPackById(packId);

        //Assert
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetPackByNonExistingId() {
        //Arrange
        int packId = 2;

        //Act
        Pack result = packManager.getPackById(packId);

        //Assert
        Assert.assertNull(result);
    }
}
