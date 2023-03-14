package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.category.Category;
import seedu.address.model.person.Person;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CategoryCard extends UiPart<Region> {

    private static final String FXML = "PersonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Category category;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label summary;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CategoryCard(Category category, int displayedIndex) {
        super(FXML);
        this.category = category;
        id.setText(displayedIndex + ". ");
        name.setText(category.getCategoryName());
        summary.setText(category.getSummary());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CategoryCard)) {
            return false;
        }

        // state check
        CategoryCard card = (CategoryCard) other;
        return id.getText().equals(card.id.getText())
                && category.equals(card.category);
    }
}
