package nl.ramonpeek.tests;

import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import nl.ramonpeek.repositories.WolfRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestWolfManager {

    private WolfManager wolfManager;

    @Before
    public void resetTestEnvironment() {
        WolfMemoryContext context = new WolfMemoryContext();
        context.setMemory(new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});
        this.wolfManager = new WolfManager(new WolfRepo(context));
    }

    @Test
    public void testGetWolfByExistingId() {
        //Arrange
        int wolfId = 0;

        //Act
        Wolf result = wolfManager.getWolfById(wolfId);

        //Assert
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetWolfByNonExistingId() {
        //Arrange
        int wolfId = 2;

        //Act
        Wolf result = wolfManager.getWolfById(wolfId);

        //Assert
        Assert.assertNull(result);
    }
}
