package seedu.vms.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui extends Refreshable {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Show error dialog and close app. */
    void showErrorDialogAndShutdown(String mainMessage, String additionalMessage);
}
