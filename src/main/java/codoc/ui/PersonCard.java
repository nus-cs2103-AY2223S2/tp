package codoc.ui;

import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/linkedinbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label course;
    @FXML
    private Label year;
    @FXML
    private Label email;

    private MainWindow mainWindow;

    private int displayedIndex;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(MainWindow mainWindow, Person person, int displayedIndex) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.person = person;
        this.displayedIndex = displayedIndex;
        id.setText(displayedIndex + "");
        String imagePath = person.getProfilePicture().imagePath;
        Image image = new Image("file:" + imagePath);
        profilePicture.setImage(image);
        name.setText(person.getName().fullName);
        year.setText("Year " + person.getYear().year);
        course.setText(person.getCourse().course);
        email.setText(person.getEmail().value);
    }

    @FXML
    private void viewPerson() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view " + displayedIndex);
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
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
