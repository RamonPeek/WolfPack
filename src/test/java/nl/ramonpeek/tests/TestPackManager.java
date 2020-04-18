package nl.ramonpeek.tests;

import nl.ramonpeek.dal.memory.PackMemoryContext;
import nl.ramonpeek.dal.memory.WolfMemoryContext;
import nl.ramonpeek.managers.PackManager;
import nl.ramonpeek.managers.WolfManager;
import nl.ramonpeek.models.Pack;
import nl.ramonpeek.models.Wolf;
import nl.ramonpeek.repositories.PackRepo;
import nl.ramonpeek.repositories.WolfRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestPackManager {

    private PackManager packManager;

    @Before
    public void resetTestEnvironment() {
        PackMemoryContext context = new PackMemoryContext();
        context.setMemory(new ArrayList<Pack>() {{
            //FILL TEST ENVIRONMENT DATA HERE
        }});
        this.packManager = new PackManager(new PackRepo(context));
    }

    @Test
    public void testGetPackByExistingId() {

    }

    @Test
    public void testGetPackByNonExistingId() {

    }
}
