package seedu.address.ui.jobs;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.commands.jobs.EditDeliveryJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;
import seedu.address.ui.UiPart;
import seedu.address.ui.person.AddressBookWindow;

/**
 * AddDeliveryJobWindow
 */
public class AddDeliveryJobWindow extends UiPart<Stage> {

    private static final String FXML = "AddDeliveryJobWindow.fxml";

    private static final String EDIT_TITLE = "Edit Delivery Job";
    private static final String EDIT_BUTTON = "Edit Job";

    private final Optional<DeliveryJob> toEdit;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private Runnable completeEditCallback;

    @FXML
    private TextField inputSender;
    @FXML
    private TextField inputRecipient;
    @FXML
    private TextField inputEarning;
    @FXML
    private TextArea inputDescription;
    @FXML
    private DatePicker inputDeliveryDate;
    @FXML
    private ChoiceBox<String> inputDeliverySlot;
    @FXML
    private Button createButton;
    @FXML
    private VBox outputErrorPlaceholder;

    /**
     * Create mode.
     */
    public AddDeliveryJobWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
        toEdit = Optional.empty();
    }

    /**
     * Edit mode.
     */
    public AddDeliveryJobWindow(Stage primaryStage, Logic logic, DeliveryJob job, Runnable callback) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
        toEdit = Optional.of(job);
        completeEditCallback = callback;
    }

    /**
     * fillInnerParts.
     */
    public void fillInnerParts() {
        inputDeliverySlot.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));

        // Adapted from
        // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();
            if (text.isEmpty() || text.equals(".")) {
                return change;
            }
            if (text.matches(Earning.VALIDATION_REGEX) || text.matches(Earning.VALIDATION_REGEX_DECI)) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        inputEarning.setTextFormatter(textFormatter);

        fillDetails();
    }

    void fillDetails() {
        toEdit.ifPresent(job -> {
            inputSender.setText(job.getSenderId());
            inputRecipient.setText(job.getRecipientId());

            job.getDeliveryDate().ifPresent(val -> {
                inputDeliveryDate.setValue(val.getDate());
            });
            job.getDeliverySlot().ifPresent(val -> {
                inputDeliverySlot.getSelectionModel().select(val.value);
            });
            job.getEarning().ifPresent(val -> {
                inputEarning.setText(val.value);
            });
            inputDescription.setText(job.getDescription());
            primaryStage.setTitle(EDIT_TITLE);
            createButton.setText(EDIT_BUTTON);
        });
    }

    @FXML
    private void viewSenderAddressBook() {
        logger.info("[Event] viewSenderAddressBook");
        AddressBookWindow addressBookWindow = new AddressBookWindow(new Stage(), logic, person -> {
            inputSender.setText(person.getPersonId());
        });
        addressBookWindow.fillInnerParts();
        addressBookWindow.show();
    }

    @FXML
    private void viewRecipientAddressBook() {
        logger.info("[Event] viewRecipientAddressBook");
        AddressBookWindow addressBookWindow = new AddressBookWindow(new Stage(), logic, person -> {
            inputRecipient.setText(person.getPersonId());
        });
        addressBookWindow.fillInnerParts();
        addressBookWindow.show();
    }

    @FXML
    private void createDeliveryJob() {
        logger.info("[Event] createDeliveryJob");
        clearError();
        if (!validateFields()) {
            return;
        }

        try {
            if (toEdit.isPresent()) {
                EditDeliveryJobCommand.EditDeliveryJobDescriptor des = prepareChange();
                logic.execute(new EditDeliveryJobCommand(des));
                completeEditCallback.run();
                getRoot().close();
            } else {
                DeliveryJob job;

                if (inputDeliverySlot.getValue() == null) {
                    job = new DeliveryJob(inputSender.getText(), inputRecipient.getText(),
                            inputEarning.getText(), inputDescription.getText());
                } else {
                    job = new DeliveryJob(inputSender.getText(), inputRecipient.getText(),
                            inputDeliveryDate.getValue().format(DeliveryDate.VALID_FORMAT),
                            inputDeliverySlot.getValue(),
                            inputEarning.getText(), inputDescription.getText());
                }

                logic.execute(new AddDeliveryJobCommand(job));
                getRoot().close();
            }
        } catch (ParseException | CommandException e) {
            logger.warning("[Event] createDeliveryJob" + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.warning("[Event] createDeliveryJob" + e.getMessage());
        }
    }

    private EditDeliveryJobCommand.EditDeliveryJobDescriptor prepareChange() {
        EditDeliveryJobCommand.EditDeliveryJobDescriptor des = new EditDeliveryJobCommand.EditDeliveryJobDescriptor();
        DeliveryJob job = toEdit.get();
        des.setJobId(job.getJobId());
        if (!inputSender.getText().equalsIgnoreCase(job.getSenderId())) {
            des.setSender(inputSender.getText());
        }

        if (!inputRecipient.getText().equalsIgnoreCase(job.getRecipientId())) {
            des.setRecipient(inputRecipient.getText());
        }

        job.getEarning().ifPresentOrElse(val -> {
            if (!inputEarning.getText().equals(val.value)) {
                des.setEarning(new Earning(inputEarning.getText()));
            }
        }, () -> {
            if (!inputEarning.getText().isEmpty()) {
                des.setEarning(new Earning(inputEarning.getText()));
            }
        });

        if (inputDeliveryDate.getValue() != null) {
            job.getDeliveryDate().ifPresent(val -> {
                if (!inputDeliveryDate.getValue().format(DeliveryDate.VALID_FORMAT).equals(val.date)) {
                    des.setDeliveryDate(
                            new DeliveryDate(inputDeliveryDate.getValue().format(DeliveryDate.VALID_FORMAT)));
                }
            });
        }

        if (inputDeliverySlot.getValue() != null) {
            job.getDeliverySlot().ifPresent(val -> {
                if (!inputDeliverySlot.getValue().equals(val.value)) {
                    des.setDeliverySlot(new DeliverySlot(inputDeliverySlot.getValue()));
                }
            });
        }

        if (!inputDescription.getText().equalsIgnoreCase(job.getDescription())) {
            des.setDescription(inputDescription.getText());
        }
        return des;
    }

    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

    boolean validateFields() {
        boolean flag = true;
        if (inputSender.getText().isEmpty()) {
            inputSender.getStyleClass().add("error-input");
            outputError("Sender cannot be empty.");
            flag = false;
        }

        if (inputRecipient.getText().isEmpty()) {
            inputRecipient.getStyleClass().add("error-input");
            outputError("Recipient cannot be empty.");
            flag = false;
        }

        if (!inputSender.getText().isEmpty() && !inputRecipient.getText().isEmpty()) {
            if (inputSender.getText().equals(inputRecipient.getText())) {
                inputSender.getStyleClass().add("error-input");
                inputRecipient.getStyleClass().add("error-input");
                outputError("Sender and Recipient cannot be the same.");
                flag = false;
            }
        }

        if (inputEarning.getText().isEmpty()) {
            inputEarning.getStyleClass().add("error-input");
            outputError("Earning cannot be empty.");
            flag = false;
        } else {
            if (!inputEarning.getText().matches(Earning.VALIDATION_REGEX)
                    && !inputEarning.getText().matches(Earning.VALIDATION_REGEX_DECI)) {
                inputEarning.getStyleClass().add("error-input");
                outputError("Invalid earning.");
                flag = false;
            }
        }

        if (inputDeliveryDate.getValue() != null || inputDeliverySlot.getValue() != null) {
            // Todo: refine rules
            try {
                if (!DeliveryDate.isValidDate(inputDeliveryDate.getValue().format(DeliveryDate.VALID_FORMAT))) {
                    inputDeliveryDate.getStyleClass().add("error-input");
                    outputError("Invalid schedule date.");
                    flag = false;
                }
            } catch (Exception e) {
                inputDeliveryDate.getStyleClass().add("error-input");
                outputError("Invalid schedule date.");
                flag = false;
            }
            try {
                if (inputDeliverySlot.getValue().isEmpty()) {
                    inputDeliverySlot.getStyleClass().add("error-input");
                    outputError("Invalid schedule slot.");
                    flag = false;
                }
            } catch (Exception e) {
                inputDeliverySlot.getStyleClass().add("error-input");
                outputError("Invalid schedule slot.");
                flag = false;
            }
        }

        return flag;
    }

    private void outputError(String msg) {
        Label err = new Label(msg);
        err.getStyleClass().add("error");
        outputErrorPlaceholder.getChildren().add(err);
    }

    private void clearError() {
        outputErrorPlaceholder.getChildren().clear();
    }

    /**
     * Show main window.
     */
    public void show() {
        logger.fine("Showing add job window");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
