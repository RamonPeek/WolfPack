package nl.ramonpeek.tests;

import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.models.Location;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.models.enums.Gender;
import nl.ramonpeek.models.enums.WolfType;
import nl.ramonpeek.repositories.PackRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackManagerTests {

    private PackManager packManager;

    @BeforeEach
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
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetPackByNonExistingId() {
        //Arrange
        int packId = 2;

        //Act
        Pack result = packManager.getPackById(packId);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testGetAllPacksIfPacksExist() {
        //Arrange

        //Act
        List<Pack> result = packManager.getAllPacks();

        //Assert
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void testGetAllPacksIfNoPacksExist() {
        //Arrange
        packManager = new PackManager(new PackRepo(new PackMemoryContext()));

        //Act
        List<Pack> result = packManager.getAllPacks();

        //Assert
        Assertions.assertEquals(result.size(), 0);
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
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getWolves().stream().filter(wolf -> wolf.getId() == packCreator.getId()).findFirst().orElse(null));
    }

    @Test
    public void testCreatePackWithoutAllProperties() {
        //Arrange
        Wolf packCreator = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(3, null, null);

        //Act
        Pack result = packManager.createPack(pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testCreatePackWithAlreadyExistingId() {
        //Arrange
        Pack pack = new Pack(0, "TestPack", new ArrayList<Wolf>());

        //Act
        Pack result = packManager.createPack(pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testAddExistingNonPresentWolfToExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(2, WolfType.ALPHA, "Jordi", "Siemens", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(0, "AlphaPack", new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});

        //Act
        Pack result = packManager.addWolfToPack(wolf, pack);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null));
    }

    @Test
    public void testAddExistingNonPresentWolfToNonExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(2, WolfType.ALPHA, "Jordi", "Siemens", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = null;

        //Act
        Pack result = packManager.addWolfToPack(wolf, pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testAddExistingPresentWolfToExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(1, "BetaPack", new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
        }});

        //Act
        Pack result = packManager.addWolfToPack(wolf, pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testAddNonExistingWolfToExistingPack() {
        //Arrange
        Wolf wolf = null;
        Pack pack = new Pack(1, "BetaPack", new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
        }});

        //Act
        Pack result = packManager.addWolfToPack(wolf, pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testAddNonExistingWolfToNonExistingPack() {
        //Arrange
        Wolf wolf = null;
        Pack pack = null;

        //Act
        Pack result = packManager.addWolfToPack(wolf, pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testRemoveExistingNonPresentWolfFromExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(0, "AlphaPack", new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});

        //Act
        Pack result = packManager.removeWolfFromPack(wolf, pack);

        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void testRemoveExistingNonPresentWolfFromNonExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(2, WolfType.ALPHA, "Jordi", "Siemens", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = null;

        //Act
        Pack result = packManager.removeWolfFromPack(wolf, pack);

        //Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testRemoveExistingPresentWolfFromExistingPack() {
        //Arrange
        Wolf wolf = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        Pack pack = new Pack(0, "AlphaPack", new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});

        //Act
        Pack result = packManager.removeWolfFromPack(wolf, pack);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getWolves().stream().filter(w -> w.getId() == wolf.getId()).findFirst().orElse(null));
    }
}
