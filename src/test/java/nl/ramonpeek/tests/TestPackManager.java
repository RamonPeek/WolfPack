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
import java.util.List;

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

    @Test
    public void testGetAllPacksIfPacksExist() {
        //Arrange

        //Act
        List<Pack> result = packManager.getAllPacks();

        //Assert
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAllWolfsIfNoWolfsExist() {
        //Arrange
        packManager = new PackManager(new PackRepo(new PackMemoryContext()));

        //Act
        List<Pack> result = packManager.getAllPacks();

        //Assert
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void testCreatePackWithAllProperties() {
        //Arrange
        Wolf packCreator = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(3, "DeltaPack", new ArrayList<Wolf>() {{
            add(packCreator);
        }});

        //Act
        Pack result = packManager.createPack(pack);

        //Assert
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getWolves().stream().filter(wolf -> wolf.getId() == packCreator.getId()).findFirst().get());
    }

    @Test
    public void testCreatePackWithoutAllProperties() {
        //Arrange
        Wolf packCreator = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(3, null, null);

        //Act
        Pack result = packManager.createPack(pack);

        //Assert
        Assert.assertNull(result);
    }

    @Test
    public void testCreatePackWithAlreadyExistingId() {
        //Arrange
        Pack pack = new Pack(0, "TestPack", new ArrayList<Wolf>());

        //Act
        Pack result = packManager.createPack(pack);

        //Assert
        Assert.assertNull(result);
    }

    @Test
    public void testAddExistingNonPresentWolfToExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testAddExistingNonPresentWolfToNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testAddExistingPresentWolfToExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testAddExistingPresentWolfToNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testAddNonExistingWolfToExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testAddNonExistingWolfToNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    /**
     *
     */

    @Test
    public void testRemoveExistingNonPresentWolfFromExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testRemoveExistingNonPresentWolfFromNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testRemoveExistingPresentWolfFromExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testRemoveExistingPresentWolfFromNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testRemoveNonExistingWolfFromExistingPack() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void testRemoveNonExistingWolfFromNonExistingPack() {
        //Arrange

        //Act

        //Assert
    }
}
