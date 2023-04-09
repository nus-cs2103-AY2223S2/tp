package seedu.address.ui;

import java.io.IOException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ImageUtil;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Person person;

    private final Logic logic;

    private final MainWindow mainWindow;

    private final int index;

    @FXML
    private HBox cardPane;

    @FXML
    private ImageView imageView;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Logic logic, MainWindow mainWindow) {
        super(FXML);
        this.person = person;
        this.logic = logic;
        this.mainWindow = mainWindow;
        this.index = displayedIndex;
        Image image;
        if (person.hasDefaultImage()) {
            image = new Image(person.getImagePath());
        } else {
            try {
                if (ImageUtil.isValidImage(person.getImagePath())) {
                    image = new Image("file:" + person.getImagePath());
                } else {
                    image = new Image(Person.getDefaultImagePath());
                }
            } catch (IOException io) {
                image = new Image(Person.getDefaultImagePath());
            }
        }
        imageView.setImage(image);
        id.setText(this.index + ". ");
        name.setText(person.getName().fullName);
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(this::createLabel);
    }

    /**
     * @param tagLabel shows the tag name
     * @return the input string with the front indicator removed
     */
    private String setLabel(String tagLabel) {
        String[] parts = tagLabel.split("XXXXX");
        String str = "";
        switch (parts[0]) {
        case "Module":
        case "Commitment":
            str = parts[1];
            break;
        default:
            str = tagLabel;
        }
        return str;
    }

    /**
     * Creates labels based on the types to be displayed.
     *
     * @param tag takes in a tag type to extract information from within
     */
    private void createLabel(Tag tag) {
        Label label = new Label(setLabel(tag.tagName));
        String colour = tag.tagColor();
        label.setStyle("-fx-background-color: " + colour + ";");
        tags.getChildren().add(label);
    }


    @FXML
    private void showPersonalPane() {
        logic.setPersonId(this.index);
        mainWindow.changeIndividualPane();
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
