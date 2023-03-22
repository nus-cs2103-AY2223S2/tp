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
import seedu.address.model.person.Person;

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

    private final int imgNumber = 9;

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
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        int imgIndex = displayedIndex % imgNumber;
        imgIndex = imgIndex == 0 ? imgNumber : imgIndex;
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        parentPhone.setText(person.getParentPhone().value + " (P)");
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        avatar.setImage(new Image(this.getClass().getResourceAsStream("/images/avatars/" + imgIndex + ".png")));
        phoneIcon1.setImage(new Image(this.getClass().getResourceAsStream("/images/icons/phone.png")));
        phoneIcon2.setImage(new Image(this.getClass().getResourceAsStream("/images/icons/phone.png")));
        addressIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/icons/address.png")));
        emailIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/icons/email.png")));
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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

    public void exportProgress() {
        ExportProgressWindow exportProgressWindow = new ExportProgressWindow(this.person);
        exportProgressWindow.show();
    }
}
