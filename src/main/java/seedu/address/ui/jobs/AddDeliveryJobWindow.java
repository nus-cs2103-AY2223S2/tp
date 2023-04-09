package seedu.address.ui.jobs;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.StringConverter;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.commands.jobs.EditDeliveryJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;
import seedu.address.ui.UiPart;
import seedu.address.ui.person.AddressBookDialog;

/**
 * AddDeliveryJobWindow
 */
public class AddDeliveryJobWindow extends UiPart<Stage> {

    private static final String FXML = "ModifyDeliveryJobWindow.fxml";

    private static final String EDIT_TITLE = "Edit Delivery Job";

    private final Optional<DeliveryJob> toEdit;
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private Optional<Consumer<CommandResult>> completeEditCallback = Optional.empty();
    private AddressBookDialog addressBookWindow;

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
    private Button editButton;
    @FXML
    private VBox outputErrorPlaceholder;

    // Solution below adapted from:
    // https://stackoverflow.com/questions/26831978/javafx-datepicker-getvalue-in-a-specific-format
    private StringConverter<LocalDate> deliveryDateConverter = new StringConverter<LocalDate>() {
        private String pattern = "yyyy-MM-dd";
        private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        {
            inputDeliveryDate.setPromptText(pattern.toLowerCase());
        }

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    // Solution below adapted from:
    // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
    private UnaryOperator<Change> earningValidator = change -> {
        String text = change.getText();
        String fullText = inputEarning.getText();
        if (text.equals(".") && fullText.split("\\d+").length < 2) {
            return change;
        }
        if (text.isEmpty() || text.matches(Earning.VALIDATION_REGEX) || text.matches(Earning.VALIDATION_REGEX_DECI)) {
            return change;
        }
        return null;
    };

    private ChangeListener<Boolean> emptyDateHandler = new ChangeListener<Boolean>() {
        @Override
        public void changed(final ObservableValue<? extends Boolean> observable,
                final Boolean oldValue, final Boolean newValue) {
            if (newValue) {
                inputDeliveryDate.setValue(null);
            }
        }
    };

    private ObservableList<String> deliverySlotOptions = FXCollections.observableArrayList(
            "",
            new DeliverySlot("1").getDescription(),
            new DeliverySlot("2").getDescription(),
            new DeliverySlot("3").getDescription(),
            new DeliverySlot("4").getDescription(),
            new DeliverySlot("5").getDescription());

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
    public AddDeliveryJobWindow(Stage primaryStage, Logic logic, DeliveryJob job) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
        this.logic = logic;
        toEdit = Optional.of(job);
    }

    /**
     * fillInnerParts.
     */
    public void fillInnerParts() {
        inputDeliverySlot.setItems(deliverySlotOptions);
        inputEarning.setTextFormatter(new TextFormatter<String>(earningValidator));
        inputDeliveryDate.getEditor().textProperty().isEmpty().addListener(emptyDateHandler);
        inputDeliveryDate.setConverter(deliveryDateConverter);

        toEdit.ifPresent(job -> {
            fillDetails(job);
            primaryStage.setTitle(EDIT_TITLE);
            createButton.setVisible(false);
            editButton.setVisible(true);
        });
    }

    private void fillDetails(DeliveryJob job) {
        inputSender.setText(job.getSenderId());
        inputRecipient.setText(job.getRecipientId());

        job.getDeliveryDate().ifPresentOrElse(val -> {
            if (val.getDate().isEqual(DeliveryDate.placeholder().getDate())) {
                inputDeliveryDate.setValue(null);
                inputDeliveryDate.getEditor().clear();
            } else {
                inputDeliveryDate.setValue(val.getDate());
            }
        }, () -> {
            inputDeliveryDate.getEditor().clear();
        });

        job.getDeliverySlot().ifPresentOrElse(val -> {
            inputDeliverySlot.getSelectionModel().select(val.getSlot());
        }, () -> {
            inputDeliverySlot.getSelectionModel().select(0);
        });

        job.getEarning().ifPresent(val -> {
            inputEarning.setText(val.value);
        });

        inputDescription.setText(job.getDescription());
    }

    @FXML
    private void viewSenderAddressBook() {
        logger.info("[Event] viewSenderAddressBook");
        if (addressBookWindow != null) {
            addressBookWindow.getRoot().close();
        }
        addressBookWindow = new AddressBookDialog(new Stage(), logic, person -> {
            inputSender.setText(person.getPersonId());
            addressBookWindow.getRoot().close();
        });
        addressBookWindow.getRoot().setTitle("Select sender");
        addressBookWindow.fillInnerParts();
        addressBookWindow.show();
    }

    @FXML
    private void viewRecipientAddressBook() {
        logger.info("[Event] viewRecipientAddressBook");
        if (addressBookWindow != null) {
            addressBookWindow.getRoot().close();
        }
        addressBookWindow = new AddressBookDialog(new Stage(), logic, person -> {
            inputRecipient.setText(person.getPersonId());
            addressBookWindow.getRoot().close();
        });
        addressBookWindow.getRoot().setTitle("Select recipient");
        addressBookWindow.fillInnerParts();
        addressBookWindow.show();
    }

    /**
     * Sets the result callback handler.
     *
     * @param handler
     */
    public void setResultHandler(Consumer<CommandResult> handler) {
        assert handler != null;

        this.completeEditCallback = Optional.of(handler);
    }

    @FXML
    private void editDeliveryJob() {
        logger.info("[Event] editDeliveryJob");
        clearError();
        if (!validateFields()) {
            return;
        }

        try {
            EditDeliveryJobCommand.EditDeliveryJobDescriptor des = prepareChange();
            CommandResult commandResult = logic.execute(new EditDeliveryJobCommand(des));
            completeEditCallback.ifPresent(handler -> {
                handler.accept(commandResult);
            });
            getRoot().close();
        } catch (FileNotFoundException | ParseException | CommandException e) {
            logger.warning("[Event] editDeliveryJob" + e.getMessage());
            outputError(e.getMessage());
        }
    }

    @FXML
    private void createDeliveryJob() {
        logger.info("[Event] createDeliveryJob");
        clearError();
        if (!validateFields()) {
            return;
        }

        try {
            DeliveryJob job;

            if (inputDeliverySlot.getValue() == null) {
                // if date and slot is empty
                job = new DeliveryJob(
                        inputRecipient.getText(),
                        inputSender.getText(),
                        inputEarning.getText(),
                        inputDescription.getText());
            } else {
                // if date and slot has value
                job = new DeliveryJob(
                        inputRecipient.getText(),
                        inputSender.getText(),
                        inputDeliveryDate.getValue().toString(),
                        Integer.toString(inputDeliverySlot.getSelectionModel().getSelectedIndex()),
                        inputEarning.getText(),
                        inputDescription.getText());
            }

            CommandResult commandResult = logic.execute(new AddDeliveryJobCommand(job));
            completeEditCallback.ifPresent(handler -> {
                handler.accept(commandResult);
            });
            getRoot().close();
        } catch (FileNotFoundException | ParseException | CommandException e) {
            logger.warning("[Event] createDeliveryJob" + e.getMessage());
            outputError(e.getMessage());
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

        if (!inputDeliveryDate.getEditor().getText().isEmpty()) {
            // date field has value
            job.getDeliveryDate().ifPresentOrElse(val -> {
                // date is different from existing value, overwrite.
                if (!val.date.equals(inputDeliveryDate.getEditor().getText())) {
                    des.setDeliveryDate(new DeliveryDate(inputDeliveryDate.getEditor().getText()));
                }
            }, () -> {
                // new date value
                des.setDeliveryDate(new DeliveryDate(inputDeliveryDate.getEditor().getText()));
            });
        } else {
            // date field is empty to overwrite existing value.
            job.getDeliveryDate().ifPresent(val -> {
                des.clearDeliveryDate();
            });
        }

        if (inputDeliverySlot.getValue() != null) {
            // slot field has value
            job.getDeliverySlot().ifPresentOrElse(val -> {
                // slot is different from existing value, overwrite.
                if (!inputDeliverySlot.getValue().equals(val.value)) {
                    des.setDeliverySlot(new DeliverySlot(
                            Integer.toString(inputDeliverySlot.getSelectionModel().getSelectedIndex())));
                }
            }, () -> {
                // new slot value
                des.setDeliverySlot(new DeliverySlot(
                            Integer.toString(inputDeliverySlot.getSelectionModel().getSelectedIndex())));
            });
        } else {
            // slot field is empty to overwrite existing value.
            job.getDeliverySlot().ifPresent(val -> {
                des.clearDeliverySlot();
            });
        }

        if (!inputDescription.getText().equalsIgnoreCase(job.getDescription())) {
            des.setDescription(inputDescription.getText());
        }
        return des;
    }

    @FXML
    private void handleExit() {
        if (addressBookWindow != null) {
            addressBookWindow.getRoot().close();;
        }
        getRoot().close();
    }

    private boolean validateFields() {
        boolean isError = true;
        if (inputSender.getText().isEmpty()) {
            inputSender.getStyleClass().add("error-input");
            outputError("Sender cannot be empty.");
            isError = false;
        }

        if (inputRecipient.getText().isEmpty()) {
            inputRecipient.getStyleClass().add("error-input");
            outputError("Recipient cannot be empty.");
            isError = false;
        }

        if (!inputSender.getText().isEmpty() && !inputRecipient.getText().isEmpty()) {
            if (inputSender.getText().equals(inputRecipient.getText())) {
                inputSender.getStyleClass().add("error-input");
                inputRecipient.getStyleClass().add("error-input");
                outputError("Sender and Recipient cannot be the same.");
                isError = false;
            }
        }

        if (inputEarning.getText().isEmpty()) {
            inputEarning.getStyleClass().add("error-input");
            outputError("Earning cannot be empty.");
            isError = false;
        }

        // any has value
        if (inputDeliveryDate.getValue() != null || inputDeliverySlot.getValue() != null) {
            boolean isDateFilled = false;
            boolean isSlotFilled = false;

            // date filled
            if (inputDeliveryDate.getValue() != null && !inputDeliveryDate.getEditor().getText().isBlank()) {
                isDateFilled = true;
            }

            // slot filled
            if (inputDeliverySlot.getValue() != null && !inputDeliverySlot.getValue().isBlank()) {
                isSlotFilled = true;
            }

            if (isDateFilled || isSlotFilled) {
                // date empty
                if (!isDateFilled) {
                    showDateError();
                    isError = false;
                } else {
                    // date filled, check format
                    if (!DeliveryDate.isValidDate(inputDeliveryDate.getEditor().getText())) {
                        showDateError();
                        isError = false;
                    }
                }

                // slot empty
                if (!isSlotFilled) {
                    showSlotError();
                    isError = false;
                } else {
                    // slot might have value, check field
                    if (inputDeliverySlot.getValue().isBlank()) {
                        showSlotError();
                        isError = false;
                    }
                }
            } else {
                inputDeliverySlot.setValue(null);
                inputDeliveryDate.getEditor().clear();
                inputDeliveryDate.setValue(null);
            }
        }

        return isError;
    }

    private void showDateError() {
        inputDeliveryDate.getStyleClass().add("error-input");
        outputError("Invalid schedule date.");
    }

    private void showSlotError() {
        inputDeliverySlot.getStyleClass().add("error-input");
        outputError("Invalid schedule slot.");
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
        handleExit();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
