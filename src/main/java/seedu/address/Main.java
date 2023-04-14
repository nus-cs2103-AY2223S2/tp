package seedu.address;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;



/**
 * The main entry point to the application.
 *
 * This is a workaround for the following error when MainApp is made the
 * entry point of the application:
 *
 *     Error: JavaFX runtime components are missing, and are required to run this application
 *
 * The reason is that MainApp extends Application. In that case, the
 * LauncherHelper will check for the javafx.graphics module to be present
 * as a named module. We don't use JavaFX via the module system so it can't
 * find the javafx.graphics module, and so the launch is aborted.
 *
 * By having a separate main class (Main) that doesn't extend Application
 * to be the entry point of the application, we avoid this issue.
 */
public class Main {
    private static void createDataFolderIfMissing() {
        Path dataFolderPath = Paths.get("data");
        if (!Files.exists(dataFolderPath)) {
            try {
                Files.createDirectory(dataFolderPath);
            } catch (IOException e) {
                System.err.println("Failed to create data folder");
                e.printStackTrace();
            }
        }
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        createDataFolderIfMissing();
        Application.launch(MainApp.class, args);
    }

}

