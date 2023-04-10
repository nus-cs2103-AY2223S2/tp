package seedu.dengue.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dengue.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String AGE_FORMAT = "Patient Age: %s y/o";
    private static final String POSTAL_FORMAT = "Postal Code: %s";
    private static final String DATE_FORMAT = "Reported on: %s";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on DengueHotspotTracker level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label age;
    @FXML
    private Label postal;
    @FXML
    private Label date;
    @FXML
    private FlowPane variants;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        age.setText(String.format(AGE_FORMAT, person.getAge().value));
        postal.setText(String.format(POSTAL_FORMAT, person.getPostal().value));
        date.setText(String.format(DATE_FORMAT, person.getDate().value));
        person.getVariants().stream()
                .sorted(Comparator.comparing(variant -> variant.variantName))
                .forEach(variant -> variants.getChildren().add(new Label(variant.variantName.toString())));
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
