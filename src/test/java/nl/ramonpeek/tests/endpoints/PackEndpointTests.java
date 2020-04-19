package nl.ramonpeek.tests.endpoints;

import nl.ramonpeek.controllers.PackController;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackEndpointTests {

    private PackController packController = new PackController(new PackManager(new PackRepo(new PackMemoryContext())), new WolfManager(new WolfRepo(new WolfMemoryContext())));

    @BeforeEach
    public void resetTestEnvironment() {
        PackMemoryContext packContext = new PackMemoryContext();
        packContext.setMemory(new ArrayList<Pack>() {{
            add(new Pack(0, "AlphaPack", new ArrayList<Wolf>() {{
                add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
                add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
            }}));
            add(new Pack(1, "BetaPack", new ArrayList<Wolf>() {{
                add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            }}));
        }});
        WolfMemoryContext wolfContext = new WolfMemoryContext();
        wolfContext.setMemory(new ArrayList<Wolf>() {{
            add(new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0)));
            add(new Wolf(1, WolfType.OMEGA, "Marije", "Janssen", Gender.FEMALE, new Date(), new Location(0, 0)));
        }});
        packController = new PackController(new PackManager(new PackRepo(packContext)), new WolfManager(new WolfRepo(wolfContext)));
    }

    @Test
    public void testGetPackByIdEndpointWithExistingId() {
        //Arrange
        int packId = 0;
        ResponseEntity<Pack> response = null;

        //Act
        try{
            response = packController.getPackById(packId);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetPackByIdEndpointWithNonExistingId() {
        //Arrange
        int packId = 2;

        //Act
        try{
            packController.getPackById(packId);
            Assertions.fail();
        }
        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testGetAllPacksEndpoint() {
        //Arrange
        ResponseEntity<List<Pack>> response = null;

        //Act
        try{
            response = packController.getAllPacks();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreatePackEndpointWithValidRequestBody() {
        //Arrange
        Pack pack = new Pack(2, "OmegaPack", new ArrayList<Wolf>());
        ResponseEntity<String> response = null;

        //Act
        try{
            response = packController.createPack(pack);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreatePackEndpointWithInvalidRequestBody() {
        //Arrange
        Pack pack = new Pack(2, "OmegaPack", null);

        //Act
        try{
            packController.createPack(pack);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testCreatePackEndpointWithAlreadyExistingRequestBody() {
        //Arrange
        Pack pack = new Pack(0, "AlphaPack", new ArrayList<Wolf>());

        //Act
        try{
            packController.createPack(pack);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testAddWolfToPackEndPointValid() {
        //Arrange
        int packId = 1;
        int wolfId = 1;
        ResponseEntity<String> response = null;

        //Act
        try{
            response = packController.addWolfToPack(wolfId, packId);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testAddWolfToPackEndPointWithNonExistingWolfIdAndExistingPackId() {
        //Arrange
        int wolfId = 2;
        int packId = 0;

        //Act
        try{
            packController.addWolfToPack(wolfId, packId);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testAddWolfToPackEndPointWithExistingWolfIdAndNonExistingPackId() {
        //Arrange
        int wolfId = 1;
        int packId = 2;

        //Act
        try{
            packController.addWolfToPack(wolfId, packId);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testRemoveWolfFromPackEndPointValid() {
        //Arrange
        int packId = 0;
        int wolfId = 1;
        ResponseEntity<String> response = null;

        //Act
        try{
            response = packController.removeWolfFromPack(wolfId, packId);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveWolfFromPackEndPointWithNonExistingWolfIdAndExistingPackId() {
        //Arrange
        int wolfId = 2;
        int packId = 0;

        //Act
        try{
            packController.removeWolfFromPack(wolfId, packId);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testRemoveWolfFromPackEndPointWithExistingWolfIdAndNonExistingPackId() {
        //Arrange
        int wolfId = 1;
        int packId = 2;

        //Act
        try{
            packController.removeWolfFromPack(wolfId, packId);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }
}
