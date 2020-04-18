package nl.ramonpeek;

import nl.ramonpeek.tests.TestPackManager;
import nl.ramonpeek.tests.TestWolfManager;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

public class TestApplication {

    private static List<Class> testClasses = new ArrayList<Class>() {{
        add(TestWolfManager.class);
        add(TestPackManager.class);
    }};

    public static void main(String[] args) {
        int totalFails = 0;
        int totalTests = 0;
        for(Class<?> testClass : testClasses) {
            Result result = JUnitCore.runClasses(testClass);
            int testClassFails = 0;
            List<String> errors = new ArrayList<String>();
            System.out.println("< ----- [" + testClass.getSimpleName() + "] ----- >");
            for(Failure failure : result.getFailures()) {
                errors.add(failure.toString());
                testClassFails++;
            }
            if(testClassFails > 0) {
                System.out.println("Finished with " + testClassFails + " errors:");
                for(String error : errors)
                    System.out.println("    - " + error);
            }else
                System.out.println("Finished with " + testClassFails + " errors.");
            totalTests += result.getRunCount();
            totalFails += testClassFails;
            System.out.println("");
        }
        System.out.println("< ----- [UnitTest Summary] ----- >");
        System.out.println("Finished with a total of " + totalFails + " errors [" + (totalTests - totalFails) + "/" + totalTests + " completed successfully].");
    }

}
