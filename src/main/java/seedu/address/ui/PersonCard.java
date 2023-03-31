package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *      AddressBook level 4</a>
     */

    public final Person person;
    private ExportProgressWindow exportProgressWindow;
    private Logic logic;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label parentPhone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView avatar;
    @FXML
    private ImageView phoneIcon1;
    @FXML
    private ImageView phoneIcon2;
    @FXML
    private ImageView addressIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private Button exportProgressButton;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, ExportProgressWindow exportProgressWindow) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        parentPhone.setText(person.getParentPhone().value + " (P)");
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        phoneIcon1.setImage(new Image(this.getClass().getResourceAsStream("/images/Phone.png")));
        phoneIcon2.setImage(new Image(this.getClass().getResourceAsStream("/images/Phone.png")));
        addressIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/Address.png")));
        emailIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/Email.png")));
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        personCardImage(person);
        this.exportProgressWindow = exportProgressWindow;
    }

    /**
     * Provides avatar based on person's gender.
     *
     * @param person a given person
     */
    private void personCardImage(Person person) {
        if (person.getTags() != null) {
            boolean isMale = false;
            boolean isFemale = false;

            for (Tag tag : person.getTags()) {
                if (tag.tagName.toLowerCase().equals("male")) {
                    isMale = true;
                } else if (tag.tagName.toLowerCase().equals("female")) {
                    isFemale = true;
                }
            }

            if (isMale && isFemale) {
                avatar.setImage(new Image(this.getClass().getResourceAsStream("/images/unisex.png")));
            } else if (isMale) {
                avatar.setImage(new Image(this.getClass().getResourceAsStream("/images/male.png")));
            } else if (isFemale) {
                avatar.setImage(new Image(this.getClass().getResourceAsStream("/images/female.png")));
            } else {
                avatar.setImage(new Image(this.getClass().getResourceAsStream("/images/unisex.png")));
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText()) && person.equals(card.person);
    }

    /**
     * Opens the export progress window or focuses on it if it's already opened.
     */
    public void exportProgress() {
        this.exportProgressWindow.setCheckedPerson(this.person);
        if (!this.exportProgressWindow.isShowing()) {
            exportProgressWindow.show();
        } else {
            exportProgressWindow.focus();
        }
    }
}
