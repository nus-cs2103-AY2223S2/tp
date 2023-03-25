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
import seedu.address.files.FilesManager;
import seedu.address.model.person.Person;



/**
 * A ui for the details of a specified person displayed at the right panel of TAB.
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
    private Label time;
    @FXML
    private Label age;
    @FXML
    private Button appointmentButton;
    @FXML
    private Button generateButton;
    @FXML
    private Button uploadButton;
    @FXML
    private ListView<UiFile> viewDisplay;

    public DetailDisplay() {
        super(FXML);
    }

    /**
     * Displays information of a specified person.
     */
    public void setInfo(Person person, ObservableList<UiFile> fileList) {
        this.person = person;
        viewDisplay.setItems(fileList);
        viewDisplay.setCellFactory(listView -> new FileList.FileListViewCell());
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        address.setText("Address: " + person.getAddress().value);

        if (person.hasTime()) {
            time.setText("Time: " + person.getTime().toString());
        } else {
            time.setText("Time: " + "N.A.");
        }

        if (person.hasAge()) {
            age.setText("Age: " + person.getAge().toString());
        } else {
            age.setText("Age: " + "N.A.");
        }

        if (person.hasMedicalCondition()) {
            medicalCondition.setText("Medical Condition: " + person.getMedicalCondition().getValue());
        } else {
            medicalCondition.setText("Medical Condition: " + "N.A.");
        }

        //@@author lxz333-reused
        //Reused from https://github.com/AY2223S1-CS2103T-T17-1/tp/tree/master/src/main/java/seedu/address/ui
        // with minor modifications
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
                filesManager.addFile();
            }
        });
    }

    public void setGenerateButton(FilesManager filesManager) {
        generateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UploadSuccess success = new UploadSuccess();
                success.show();
            }
        });
    }

    /**
     * Clears any information being displayed.
     */
    public void clearDetailDisplay() {
        name.setText(null);
        age.setText(null);
        phone.setText(null);
        email.setText(null);
        tags.getChildren().clear();
        time.setText(null);
        address.setText(null);
        medicalCondition.setText(null);
    }

    public void hideAppointmentButton() {
        appointmentButton.setVisible(false);
    }

    public void showAppointmentButton() {
        appointmentButton.setVisible(true);
    }

    public void hideGenerateButton() {
        generateButton.setVisible(false);
    }

    public void showGenerateButton() {
        generateButton.setVisible(true);
    }

    public void hideUploadButton() {
        uploadButton.setVisible(false);
    }

    public void showUploadButton() {
        uploadButton.setVisible(true);
    }

    public void hideViewDisplay() {
        viewDisplay.setVisible(false);
    }

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

