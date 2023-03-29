package seedu.address.ui;


import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Welcome page
 *
 * @author Haiqel Bin Hanaffi
 */
public class LoginWindow extends UiPart<Stage> {
    private static final String FXML = "LoginWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts
    private WelcomeSection welcomeSection;
    private LoadingSection loadingSection;
    private CreatePasswordSection createPasswordSection;

    @FXML
    private VBox container;

    /**
     * Default constructor for Welcome page
     * @param primaryStage Primary Stage of the whole application
     */
    public LoginWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
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

    /**
     * Show the stage
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the stage
     */
    void close() {
        primaryStage.close();
    }

    /**
     * Fill the Vbox with WelcomeNewUserSection
     */
    public void fillWelcomeNewUserSection() {
        welcomeSection = new WelcomeSection();
        container.getChildren().add(welcomeSection.getRoot());
    }

    /**
     * Fill the VBox with creating new password page
     */
    public void fillCreateNewPasswordSection() {
        createPasswordSection = new CreatePasswordSection();
        container.getChildren().clear();
        //container.getChildren().removeAll(welcomeSection.getRoot());
        container.getChildren().add(createPasswordSection.getRoot());
    }

    /**
     * Fill the Vbox with the loading page
     */
    public void fillLoadingSection() {
        loadingSection = new LoadingSection();
        container.getChildren().clear();
        //container.getChildren().removeAll(welcomeSection.getRoot());
        container.getChildren().add(loadingSection.getRoot());
    }

    /**
     * Replaces the current loading text with password success text
     */
    public void fillPasswordSuccessLoadingSection() {
        loadingSection.setPasswordSuccessText();
    }


}
