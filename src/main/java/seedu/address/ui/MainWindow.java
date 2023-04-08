package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "Base.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final HelpWindow helpWindow;
    // Independent Ui parts residing in this Ui container
    private ClientListPanel clientListPanel;
    private PolicyListPanel policyListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane clientLabel;

    @FXML
    private StackPane appointmentLabel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane policyListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());


        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Populate client list
        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        // Populate client label with first client in client list
        Client selectedClient = logic.getSelectedClient();
        int selectedClientIndex = logic.getSelectedClientIndex();
        ClientLabel selectedClientCard = new ClientLabel(selectedClient, selectedClientIndex);
        AppointmentLabel selectedAppointmentCard = new AppointmentLabel(selectedClient);

        if (selectedClient.equals(new Client())) {
            selectedClientCard = new ClientLabel();
            selectedAppointmentCard = new AppointmentLabel();
        }

        if (clientLabel.getChildren().size() > 0) {
            clientLabel.getChildren().remove(0);
            appointmentLabel.getChildren().remove(0);
        }
        clientLabel.getChildren().add(selectedClientCard.getRoot());
        appointmentLabel.getChildren().add(selectedAppointmentCard.getRoot());

        // Populate policy list of selected client
        policyListPanel = new PolicyListPanel(logic.getFilteredPolicyList());
        policyListPanelPlaceholder.getChildren().add(policyListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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
     * Selects a client based on user input.
     */
    public void handleSelect() {
        Client selectedClient = logic.getSelectedClient();
        int selectedClientIndex = logic.getSelectedClientIndex();

        ClientLabel selectedClientCard = new ClientLabel(selectedClient, selectedClientIndex);
        AppointmentLabel selectedAppointmentCard = new AppointmentLabel(selectedClient);

        if (selectedClient.equals(new Client())) {
            selectedClientCard = new ClientLabel();
            selectedAppointmentCard = new AppointmentLabel();
        }

        if (clientLabel.getChildren().size() > 0) {
            clientLabel.getChildren().remove(0);
            appointmentLabel.getChildren().remove(0);
        }
        clientLabel.getChildren().add(selectedClientCard.getRoot());
        appointmentLabel.getChildren().add(selectedAppointmentCard.getRoot());

        // Populate policy list of selected client
        policyListPanel = new PolicyListPanel(logic.getFilteredPolicyList());
        policyListPanelPlaceholder.getChildren().remove(0);
        policyListPanelPlaceholder.getChildren().add(policyListPanel.getRoot());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public ClientListPanel getClientListPanel() {
        return clientListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            String feedbackToUser = commandResult.getFeedbackToUser();
            logger.info("Result: " + feedbackToUser);
            resultDisplay.setFeedbackToUser(feedbackToUser);

            handleSelect();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
