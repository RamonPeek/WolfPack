package nl.ramonpeek.tests;

import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.WolfRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestWolfManager {

    private WolfManager wolfManager;

    @Before
    public void resetTestEnvironment() {
        WolfMemoryContext context = new WolfMemoryContext();
        context.setMemory(new ArrayList<Wolf>() {{
            //FILL TEST ENVIRONMENT DATA HERE
        }});
        this.wolfManager = new WolfManager(new WolfRepo(context));
    }

    @Test
    public void testGetWolfByExistingId() {

    }

    @Test
    public void testGetWolfByNonExistingId() {

    }
}
