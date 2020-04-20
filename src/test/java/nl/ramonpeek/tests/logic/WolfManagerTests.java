package nl.ramonpeek.tests.logic;

import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import nl.ramonpeek.repositories.WolfRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WolfManagerTests {

    private WolfManager wolfManager;

    @BeforeEach
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
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetWolfByNonExistingId() {
        //Arrange
        int wolfId = 2;

        //Act
        Wolf result = wolfManager.getWolfById(wolfId);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testGetAllWolfsIfWolfsExist() {
        //Arrange

        //Act
        List<Wolf> result = wolfManager.getAllWolves();

        //Assert
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAllWolfsIfNoWolfsExist() {
        //Arrange
        wolfManager = new WolfManager(new WolfRepo(new WolfMemoryContext()));

        //Act
        List<Wolf> result = wolfManager.getAllWolves();

        //Assert
        Assertions.assertEquals(result.size(), 0);
    }

    @Test
    public void testCreateWolfWithAllProperties() {
        //Arrange
        Wolf wolf = new Wolf(3, WolfType.BETA, "Nathan", "van", "Huizens", Gender.MALE, new Date(), new Location(0,0));

        //Act
        Wolf result = wolfManager.createWolf(wolf);

        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void testCreateWolfWithRequiredProperties() {
        //Arrange
        Wolf wolf = new Wolf(3, WolfType.BETA, "Marieke", "Visser", Gender.FEMALE, new Date());

        //Act
        Wolf result = wolfManager.createWolf(wolf);

        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void testCreateWolfWithoutRequiredProperties() {
        //Arrange
        Wolf wolf = new Wolf(3, null, "Stan", null, Gender.MALE, null);

        //Act
        Wolf result = wolfManager.createWolf(wolf);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testCreateWolfWithAlreadyExistingId() {
        //Arrange
        Wolf wolf = new Wolf(0, WolfType.OMEGA, "Pieter", "Bruijnsma", Gender.MALE, new Date(), new Location(0,0));

        //Act
        Wolf result = wolfManager.createWolf(wolf);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testDeleteExistingWolf() {
        //Arrange
        Wolf wolf = wolfManager.getWolfById(0);

        //Act
        Wolf result = wolfManager.deleteWolf(wolf);

        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void testDeleteNonExistingWolf() {
        //Arrange
        Wolf wolf = wolfManager.getWolfById(2);

        //Act
        Wolf result = wolfManager.deleteWolf(wolf);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testUpdateExistingWolfWithWolfWithAllProperties() {
        //Arrange
        Wolf inputWolf = wolfManager.getWolfById(0);
        Wolf updatedWolf = new Wolf(0, WolfType.ALPHA, "Jan", "van", "Hogeveen", Gender.MALE, new Date(), new Location(0, 0));

        //Act
        Wolf result = wolfManager.updateWolf(inputWolf, updatedWolf);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getType(), WolfType.ALPHA);
        Assertions.assertNotNull(result.getMiddleName());
    }

    @Test
    public void testUpdateExistingWolfWithWolfWithRequiredProperties() {
        //Arrange
        Wolf inputWolf = wolfManager.getWolfById(0);
        Wolf updatedWolf = new Wolf(0, WolfType.OMEGA, "Jan", "Hogeveen", Gender.MALE, new Date());

        //Act
        Wolf result = wolfManager.updateWolf(inputWolf, updatedWolf);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertSame(result.getType(), WolfType.OMEGA);
    }

    @Test
    public void testUpdateExistingWolfWithWolfWithoutRequiredProperties() {
        //Arrange
        Wolf inputWolf = wolfManager.getWolfById(0);
        Wolf updatedWolf = new Wolf(0, null, "Jan", "Hogeveen", Gender.MALE, null);

        //Act
        Wolf result = wolfManager.updateWolf(inputWolf, updatedWolf);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testUpdateNonExistingWolf() {
        //Arrange
        Wolf inputWolf = wolfManager.getWolfById(3);
        Wolf updatedWolf = new Wolf(0, WolfType.OMEGA, "Jan", "Hogeveen", Gender.MALE, new Date());

        //Act
        Wolf result = wolfManager.updateWolf(inputWolf, updatedWolf);

        //Assert
        Assertions.assertNull(result);
    }
}
