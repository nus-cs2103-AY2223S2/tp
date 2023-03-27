package seedu.address.ui.login;

import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

import java.util.logging.Logger;

/**
 * The login window that will display the initial login UI to the user before the MainWindow appears.
 *
 * @author Haiqel Bin Hanaffi
 */
public class LoginWindow extends UiPart<Stage> {

    private static final String FXML = "";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;

    private Logic logic;

    // Independent ui parts residing in this Ui container

    /**
     * Creates a {@code LoginWindow} with the given {@code Stage} and {@code Logic}.
     */
    public LoginWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }


}
