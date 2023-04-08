package seedu.socket;

import javafx.application.Application;

/**
 * The main entry point to the application.
 *
 * This is a workaround for the following error when {@code MainApp} is made the
 * entry point of the application:
 *
 *     {@code Error: JavaFX runtime components are missing, and are required to run this application}
 *
 * The reason is that {@code MainApp} extends {@code Application}. In that case, the
 * {@code LauncherHelper} will check for the {@code javafx.graphics} module to be present
 * as a named module. We don't use JavaFX via the module system so it can't
 * find the {@code javafx.graphics} module, and so the launch is aborted.
 *
 * By having a separate main class ({@code Main}) that doesn't extend {@code Application}
 * to be the entry point of the application, we avoid this issue.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
