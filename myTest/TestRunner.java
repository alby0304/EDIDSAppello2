package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class runs the ListAdapterTest tests.
 * 
 * @see ListAdapterTest
 */
public class TestRunner {

    /**
     * Runs the tests in ListAdapterTest and prints on standard output the results.
     * 
     * @param args The command line arguments. This parameter is ignored.
     */
    public static void main(String args[]) {
        System.out.print("Running tests... ");
        Result result = JUnitCore.runClasses(MapAdapterTest.class);
        System.out.println("Done!\n");

        System.out.println(result.wasSuccessful() ? "The testing was successful!\n" : "The testing failed!\n");

        System.out.println(result.getRunCount() + " tests were performed.");
        // System.out.println(result.getIgnoreCount() + " tests were ignored.");
        System.out.println(result.getFailureCount() + " tests failed.");

        if (result.getFailureCount() != 0)
            System.out.println("\nFailed tests:");

        for (Failure failure : result.getFailures())
            System.out.println("   " + failure);

        System.out.println("\nThe test took " + result.getRunTime() + " milliseconds.\n");
    }

    /**
     * Empty constructor.
     */
    public TestRunner() {
    }
}
