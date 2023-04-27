package seedu.wife.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.wife.model.food.Food;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class FoodCard extends UiPart<Region> {

    private static final String FXML = "FoodListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Food food;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label unit;
    @FXML
    private Label quantity;
    @FXML
    private Label expiryDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FoodCode} with the given {@code Food} and index to display.
     */
    public FoodCard(Food food, int displayedIndex) {
        super(FXML);
        this.food = food;
        id.setText(displayedIndex + ". ");
        name.setText(food.getName().toString());
        unit.setText(food.getUnit().toString());
        quantity.setText(food.getQuantity().toString());
        expiryDate.setText(food.getExpiryDate().toString());
        food.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.getTagName()))
                .forEach(tag -> tags.getChildren().add(new Label(tag.getTagName())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FoodCard)) {
            return false;
        }

        // state check
        FoodCard card = (FoodCard) other;
        return id.getText().equals(card.id.getText())
                && food.equals(card.food);
    }
}
