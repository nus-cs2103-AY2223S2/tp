package fasttrack.ui;

import fasttrack.model.category.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Category}.
 */
public class SuggestionCard extends UiPart<Region> {

    private static final String FXML = "SuggestionListCard.fxml";


    public final Category category;

    @FXML
    private HBox cardPane;
    @FXML
    private Label categoryName;

    /**
     * Creates a {@code CategoryCard} with the given {@code Category} and index to display.
     */
    public SuggestionCard(Category category) {
        super(FXML);
        this.category = category;
        categoryName.setText(category.getCategoryName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SuggestionCard)) {
            return false;
        }

        // state check
        SuggestionCard card = (SuggestionCard) other;
        return category.equals(card.category);
    }
}
