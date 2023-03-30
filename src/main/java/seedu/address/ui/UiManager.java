package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.ui.events.AttemptLoginEvent;
import seedu.address.ui.events.CreatePasswordSuccessfulEvent;
import seedu.address.ui.events.LoginFailEvent;
import seedu.address.ui.events.ProceedCreatePasswordEvent;
import seedu.address.ui.events.SkipCreatePasswordEvent;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/address_book_32.png";

    private Logic logic;
    private MainWindow mainWindow;
    private LoginWindow loginWindow;

    private Stage primaryStage;
    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        logger.info("Starting UI...");

        //Set the application icon.
        this.primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            this.showLoginWindow();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    /**
     * Shows the main window which is the main application
     */
    public void showMainWindow() {
        mainWindow = new MainWindow(this.primaryStage, logic);
        mainWindow.show(); //This should be called before creating other UI parts
        mainWindow.fillInnerParts();
    }

    /**
     * Shows the welcome page
     * @throws IOException Input Output Exception
     */
    public void showLoginWindow() {
        try {
            loginWindow = new LoginWindow(this.primaryStage, logic);
            loginWindow.show();
            loginWindow.fillWelcomeSection();
            this.attachEventHandlers();
        } catch (Throwable e) {
            String error = e.getCause().getMessage();
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error when switching scenes", e);
        }
    }

    /**
     * Switches the next scene to enter password page
     * @param event ProceedCreatePasswordEvent
     */
    private void onProceedCreatePassword(ProceedCreatePasswordEvent event) {
        try {
            loginWindow.fillCreateNewPasswordSection();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error when switching scenes", e);
        }
    }

    /**
     * Switches the current stage to another stage that holds the MainWindow
     * @param event SkipCreatePasswordEvent
     */
    private void onSkipCreatePassword(SkipCreatePasswordEvent event) {
        try {
            // show the loading section first
            loginWindow.fillLoadingSection();
            // then show the real app after X seconds
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(currentEvent -> {
                loginWindow.close();
                this.showMainWindow();
            });
            delay.play();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error when switching stage", e);
        }
    }

    /**
     * Switches the current scene to show password created successfully scene
     * @param event CreatePasswordSuccessfulEvent
     */
    private void onCreatePasswordSuccess(CreatePasswordSuccessfulEvent event) {
        try {
            // save the password
            logic.setUserHashedPassword(event.getHashedPassword());
            this.launchModcheckWindow();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error when switching scenes", e);
        }
    }

    /**
     * Switches the current scene to its respective scene based on the user's
     * login attempt
     * @param event AttemptLoginEvent
     */
    private void onAttemptLogin(AttemptLoginEvent event) {
        try {
            String storedPassword = logic.getUserHashedPassword();
            String userSubmittedPassword = event.getHashedPassword();
            if (storedPassword.equals(userSubmittedPassword)) {
                // show the loading section first
                loginWindow.fillLoadingSection();
                // then show the real app after X seconds
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(currentEvent -> {
                    loginWindow.close();
                    this.showMainWindow();
                });
                delay.play();
            } else {
                LoginFailEvent loginFailEvent = new LoginFailEvent();
                Event.fireEvent(this.primaryStage, loginFailEvent);
            }
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error when switching scenes", e);
        }
    }

    private void attachEventHandlers() {
        // Observer design pattern is used here to register events
        this.primaryStage.addEventHandler(ProceedCreatePasswordEvent.PROCEED_CREATE_PASSWORD,
                this::onProceedCreatePassword);
        this.primaryStage.addEventHandler(SkipCreatePasswordEvent.SKIP_CREATE_PASSWORD_EVENT,
                this::onSkipCreatePassword);
        this.primaryStage.addEventHandler(CreatePasswordSuccessfulEvent.CREATE_PASSWORD_SUCCESSFUL_EVENT,
                this::onCreatePasswordSuccess);
        this.primaryStage.addEventHandler(AttemptLoginEvent.ATTEMPT_LOGIN_EVENT,
                this::onAttemptLogin);
    }

    private void launchModcheckWindow() {
        loginWindow.fillLoadingSection();
        loginWindow.fillPasswordSuccessLoadingSection();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(currentEvent -> {
            loginWindow.close();
            this.showMainWindow();
        });
        delay.play();
    }

    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters.
     * This method only returns after the user has closed the alert dialog.
     */
    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setId(ALERT_DIALOG_PANE_FIELD_ID);
        alert.showAndWait();
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     */
    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

}
