package nl.ramonpeek.tests.endpoints;

import nl.ramonpeek.controllers.WolfController;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testGetWolfByIdEndpointWithExistingId() {
        //Arrange
        int wolfId = 0;
        ResponseEntity<Wolf> response = null;

        //Act
        try{
            response = wolfController.getWolfById(wolfId);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetWolfByIdEndpointWithNonExistingId() {
        //Arrange
        int wolfId = 2;

        //Act
        try{
            wolfController.getWolfById(wolfId);
            Assertions.fail();
        }
        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testGetAllWolvesEndpoint() {
        //Arrange
        ResponseEntity<List<Wolf>> response = null;

        //Act
        try{
            response = wolfController.getAllWolves();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreateWolfEndpointWithValidRequestBody() {
        //Arrange
        Wolf wolf = new Wolf(2, WolfType.BETA, "Thomas", "Martens", Gender.MALE, new Date());
        ResponseEntity<String> response = null;

        //Act
        try{
            response = wolfController.createWolf(wolf);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateWolfEndpointWithInvalidRequestBody() {
        //Arrange
        Wolf wolf = new Wolf(2, WolfType.BETA, "Yannick", "Deers", null, new Date());

        //Act
        try{
            wolfController.createWolf(wolf);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testCreateWolfEndpointWithAlreadyExistingRequestBody() {
        //Arrange
        Wolf wolf = new Wolf(0, WolfType.BETA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));

        //Act
        try{
            wolfController.createWolf(wolf);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testDeleteWolfEndpointWithExistingWolfId() {
        //Arrange
        int wolfId = 0;
        ResponseEntity<String> response = null;

        //Act
        try{
            response = wolfController.deleteWolf(wolfId);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteWolfEndpointWithNonExistingWolfId() {
        //Arrange
        int wolfId = 2;

        //Act
        try{
            wolfController.deleteWolf(wolfId);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testUpdateWolfEndpointWithValidRequestBody() {
        //Arrange
        int wolfId = 0;
        Wolf updatedWolf = new Wolf(0, WolfType.ALPHA, "Jan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));
        ResponseEntity<String> response = null;

        //Act
        try{
            response = wolfController.updateWolf(wolfId, updatedWolf);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateWolfEndpointWithInvalidRequestBody() {
        //Arrange
        int wolfId = 0;
        Wolf updatedWolf = new Wolf(0, WolfType.ALPHA, "Jan", "Pieters", null, new Date(), new Location(0, 0));

        //Act
        try{
            wolfController.updateWolf(wolfId, updatedWolf);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testUpdateWolfEndpointWithNonExistingWolfId() {
        //Arrange
        int wolfId = 2;
        Wolf updatedWolf = new Wolf(2, WolfType.ALPHA, "Johan", "Pieters", Gender.MALE, new Date(), new Location(0, 0));

        //Act
        try{
            wolfController.updateWolf(wolfId, updatedWolf);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testUpdateWolfLocationEndpointWithValidRequestBody() {
        //Arrange
        int wolfId = 0;
        Location location = new Location(50, 60);
        ResponseEntity<String> response = null;

        //Act
        try{
            response = wolfController.updateLocation(wolfId, location);
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.fail();
        }
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateWolfLocationEndpointWithNonExistingWolfId() {
        //Arrange
        int wolfId = 2;
        Location location = new Location(50, 60);

        //Act
        try{
            wolfController.updateLocation(wolfId, location);
            Assertions.fail();
        }

        //Assert
        catch(ResponseStatusException exception) {
            Assertions.assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
        }
    }


}
