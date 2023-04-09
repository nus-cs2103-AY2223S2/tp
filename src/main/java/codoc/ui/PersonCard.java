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
import javafx.scene.layout.StackPane;

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
    private StackPane profilePicture;
    @FXML
    private Label course;
    @FXML
    private Label year;
    @FXML
    private Label email;

    private int displayedIndex;
    private MainWindow.ClickListener clickListener;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        this.displayedIndex = displayedIndex;
        id.setText(displayedIndex + "");
        Image image = new Image(person.getProfilePicture().profilePicturePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        profilePicture.getChildren().set(0, imageView);
        name.setText(person.getName().fullName);
        year.setText("Year " + person.getYear().year);
        course.setText(person.getCourse().course);
        email.setText(person.getEmail().value);
    }

    public void setClickListener(MainWindow.ClickListener listener) {
        this.clickListener = listener;
    }

    @FXML
    private void viewPerson() throws CommandException, ParseException {
        String index = id.getText();
        clickListener.viewIndex(index);
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
