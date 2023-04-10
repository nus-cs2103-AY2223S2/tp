package seedu.recipe.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.recipe.model.recipe.Recipe;

/**
 * A UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

    private static final String FXML = "RecipeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Recipe recipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label desc;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public RecipeCard(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        id.setText(displayedIndex + ". ");
        if (recipe.isStarred()) {
            title.setText(recipe.getTitle().title + " ★ " + System.lineSeparator()
                    + "[Price: $" + String.format("%.2f", recipe.getCost()) + "]");
        } else {
            title.setText(recipe.getTitle().title + System.lineSeparator()
                          + "[Price: $" + String.format("%.2f", recipe.getCost()) + "]");
        }
        desc.setText(recipe.getDesc().description);

        recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tmp = new Label(tag.tagName);
                    tmp.setWrapText(true);
                    tmp.setMaxWidth(230);
                    tags.getChildren().add(tmp);
                });

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        RecipeCard card = (RecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }
}
