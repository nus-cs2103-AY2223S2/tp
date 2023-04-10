package fasttrack.ui;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;

/**
 * Helper class for JavaFX UI test
 */
public class JavaFxTestHelper {
    private static boolean isInitialized = false;

    /**
     * Initialises JavaFX platform for UI test
     */
    public static void initJavaFxHelper() throws InterruptedException {
        if (!isInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            latch.await();
            isInitialized = true;
        }
    }
}
