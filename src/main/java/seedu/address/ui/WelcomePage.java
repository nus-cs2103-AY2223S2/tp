package seedu.address.ui;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.events.ProceedCreatePasswordEvent;

public class WelcomePage extends UiPart {
    private static final String FXML = "WelcomePage.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    @FXML
    private TextField inputField;

    @FXML
    private VBox container;

    /**
     * Default constructor for Welcome page
     * @param primaryStage Primary Stage of the whole application
     */
    public WelcomePage(Stage primaryStage) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
    }

    /**
     * Handles the event when the user submits his/her response
     */
    @FXML
    private void handleSubmit() {
        String input = inputField.getText().toLowerCase();
        if (input.equals("yes") || input.equals("y")) {
            ProceedCreatePasswordEvent proceedCreatePasswordEvent = new ProceedCreatePasswordEvent();
            Event.fireEvent(container, proceedCreatePasswordEvent);
        }
    }


}
