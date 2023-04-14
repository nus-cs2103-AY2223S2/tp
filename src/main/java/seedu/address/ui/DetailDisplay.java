package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.files.FilesManager;
import seedu.address.model.person.Person;



/**
 * The UI component that is responsible for displaying detailed information of a patient.
 */
public class DetailDisplay extends UiPart<Region> {

    private static final String FXML = "DetailDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;

    private FileList filelist;
    private AddAppointmentWindow addAppointmentWindow;
    private ListView<Person> personListView;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label address;
    @FXML
    private Label medicalCondition;
    @FXML
    private Label nric;
    @FXML
    private Label age;
    @FXML
    private Label appointment;
    @FXML
    private Button appointmentButton;
    @FXML
    private Button generateButton;
    @FXML
    private Button uploadButton;
    @FXML
    private ListView<UiFile> viewDisplay;

    /**
     * Constructor for DetailDisplay.
     * @param commandExecutor Executor for the input commands.
     * @param personListView List of Patients for view.
     */
    public DetailDisplay(CommandBox.CommandExecutor commandExecutor, ListView<Person> personListView) {
        super(FXML);
        this.addAppointmentWindow = new AddAppointmentWindow(commandExecutor, new Stage(), personListView);
    }

    /**
     * Displays information of a specified patient.
     */
    public void setInfo(Person person, ObservableList<UiFile> fileList) {
        this.person = person;
        fileList.sorted(new FileComparator());
        viewDisplay.setItems(fileList);
        viewDisplay.setCellFactory(listView -> new FileList.FileListViewCell());
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        address.setText("Address: " + person.getAddress().value);

        if (person.hasNric()) {
            nric.setText(person.getNric().toString());
        } else {
            nric.setText("NRIC: " + "N.A.");
        }

        if (person.hasAge()) {
            age.setText(person.getAge().toString());
        } else {
            age.setText("Age: " + "N.A.");
        }

        if (person.hasMedicalCondition()) {
            medicalCondition.setText("Medical Condition: " + person.getMedicalCondition().getValue());
        } else {
            medicalCondition.setText("Medical Condition: " + "N.A.");
        }

        if (person.hasAppointment()) {
            appointment.setText(person.getAppointment().toString());
        } else {
            appointment.setText("No appointment yet");
        }

        //@@author lxz333-reused
        //Reused from https://github.com/AY2223S1-CS2103T-T17-1/tp/tree/master/src/main/java/seedu/address/ui
        // with minor modifications
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tagLabel.setMaxWidth(400);
                    tagLabel.setWrapText(true);
                    tags.getChildren().add(tagLabel);
                });
        //@@author
    }

    public void setUploadButton(FilesManager filesManager) {
        uploadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearDetailDisplay();
                hideAppointmentButton();
                hideUploadButton();
                hideGenerateButton();
                hideViewDisplay();
                try {
                    filesManager.addFile();
                } catch (RuntimeException e) {
                    medicalCondition.setText("uploaded file is not qualified");
                }
                medicalCondition.setText("click the person gain to see updated file list");
            }
        });

    }

    public void setGenerateButton(FilesManager filesManager) {
        generateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearDetailDisplay();
                hideAppointmentButton();
                hideUploadButton();
                hideGenerateButton();
                hideViewDisplay();
                AddMcInfo addMcInfo = new AddMcInfo(new Stage(), new FilesManager(person));
                if (addMcInfo.getCounter() > 1) {
                    medicalCondition.setText("Can not open multiple window for generate MC at the same time");
                    return;
                }
                if (!addMcInfo.isShowing()) {
                    addMcInfo.showAddAppointmentWindow();
                } else {
                    addMcInfo.requestFocus();
                }
                medicalCondition.setText("click the person again to see updated file list");
            }
        });
    }

    /**
     * Show the AddAppointmentWindow or requests that this AddPatientWindow get the input focus.
     */
    @FXML
    private void showAddAppointmentWindow() {
        if (!addAppointmentWindow.isShowing()) {
            addAppointmentWindow.showAddAppointmentWindow();
        } else {
            addAppointmentWindow.requestFocus();
        }
    }

    /**
     * Clears all the inforamtion of the current displayed patient.
     */
    public void clearDetailDisplay() {
        name.setText(null);
        age.setText(null);
        phone.setText(null);
        email.setText(null);
        tags.getChildren().clear();
        nric.setText(null);
        address.setText(null);
        medicalCondition.setText(null);
        appointment.setText(null);
    }

    /**
     * Hide the appointment button.
     */
    public void hideAppointmentButton() {
        appointmentButton.setVisible(false);
    }

    /**
     * Show the appointment button.
     */
    public void showAppointmentButton() {
        appointmentButton.setVisible(true);
    }

    /**
     * Hide the generate button.
     */
    public void hideGenerateButton() {
        generateButton.setVisible(false);
    }

    /**
     * Show the generate button.
     */
    public void showGenerateButton() {
        generateButton.setVisible(true);
    }

    /**
     * Hide the upload button.
     */
    public void hideUploadButton() {
        uploadButton.setVisible(false);
    }

    /**
     * Show the upload button.
     */
    public void showUploadButton() {
        uploadButton.setVisible(true);
    }

    /**
     * Hide the view display pane.
     */
    public void hideViewDisplay() {
        viewDisplay.setVisible(false);
    }

    /**
     * Show the view display pane.
     */
    public void showViewDisplay() {
        viewDisplay.setVisible(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DetailDisplay)) {
            return false;
        }

        // state check
        DetailDisplay dd = (DetailDisplay) other;
        return person.equals(dd.person);
    }
}

