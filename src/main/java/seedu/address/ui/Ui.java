package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /**
     * Executes the auto feeding reminder feature for Ui
     */
    void executeFeedingReminderInitUi();

}
