package seedu.vms.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.vms.commons.core.GuiSettings;
import seedu.vms.logic.Logic;
import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.ui.appointment.AppointmentCard;
import seedu.vms.ui.patient.PatientCard;
import seedu.vms.ui.patient.SimplifiedPatientCard;
import seedu.vms.ui.vaccination.SimplifiedVaxTypeCard;
import seedu.vms.ui.vaccination.VaxTypeCard;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> implements Refreshable {

    private static final String FXML = "MainWindow.fxml";

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ListViewPanel<IdData<Patient>> patientListPanel;
    private DetailedView<IdData<Patient>> detailedPatientView;
    private ListViewPanel<VaxType> vaxTypeListPanel;
    private DetailedView<VaxType> detailedVaxTypeView;
    private ListViewPanel<IdData<Appointment>> appointmentListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML private StackPane commandBoxPlaceholder;

    @FXML private MenuItem helpMenuItem;

    @FXML private StackPane patientListPanelPlaceholder;
    @FXML private VBox detailedPatientPanel;

    @FXML private StackPane vaxTypeListPanelPlaceholder;
    @FXML private VBox detailedVaxTypePanel;

    @FXML private StackPane appointmentListPanelPlaceholder;

    @FXML private VBox resultDisplayPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        logic.setCloseAction(() -> Platform.runLater(this::handleExit));
        logic.setShowHelpAction(() -> Platform.runLater(this::handleHelp));

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    @Override
    public void refresh() {
        resultDisplay.refresh();
        patientListPanel.refresh();
        detailedPatientView.refresh();
        vaxTypeListPanel.refresh();
        detailedVaxTypeView.refresh();
        appointmentListPanel.refresh();
    }


    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // @@author francisyzy
        // patients
        patientListPanel = new ListViewPanel<>(
                logic.getFilteredPatientMap(),
                idData -> new SimplifiedPatientCard(idData).getRoot());
        patientListPanelPlaceholder.getChildren().add(patientListPanel);
        detailedPatientView = new DetailedView<>(logic.detailedPatientProperty(),
                data -> new PatientCard(data.getValue(), data.getId() + 1).getRoot());
        detailedPatientPanel.getChildren().add(detailedPatientView);

        // @@author daitenshionyan
        vaxTypeListPanel = new ListViewPanel<>(
                logic.getFilteredVaxTypeMap(),
                (index, vaxType) -> new SimplifiedVaxTypeCard(index, vaxType).getRoot());
        vaxTypeListPanelPlaceholder.getChildren().add(vaxTypeListPanel);
        detailedVaxTypeView = new DetailedView<>(
                logic.detailedVaxTypeProperty(),
                vaxType -> new VaxTypeCard(vaxType).getRoot());
        detailedVaxTypePanel.getChildren().add(detailedVaxTypeView);
        logic.bindVaccinationDisplayList(vaxTypeListPanel.getDisplayList());

        // @@author nusE0726844
        appointmentListPanel = new ListViewPanel<>(
                logic.getFilteredAppointmentMap(),
                idData -> new AppointmentCard(idData.getValue(), idData.getId() + 1).getRoot());
        appointmentListPanelPlaceholder.getChildren().add(appointmentListPanel);

        // @@author
        resultDisplay = new ResultDisplay();
        Region resultDisplayRegion = resultDisplay.getRoot();
        resultDisplayPlaceholder.getChildren().add(resultDisplayRegion);
        logic.setOnExecutionCompletion(resultDisplay::queueMessages);

        CommandBox commandBox = new CommandBox(logic::queue);
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
}
