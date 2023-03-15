package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.category.Category;

/**
 * A UI component that displays information of a {@code Category}.
 */
public class CategoryCard extends UiPart<Region> {

    private static final String FXML = "CategoryListCard.fxml";


    public final Category category;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label categoryName;
    @FXML
    private Label expenseCount;

    /**
     * Creates a {@code CategoryCard} with the given {@code Category} and index to display.
     */
    public CategoryCard(Category category, int displayedIndex) {
        super(FXML);
        this.category = category;
        id.setText(displayedIndex + ". ");
        categoryName.setText(category.getCategoryName());
        //TODO add a function to count expenses under a specific category
        expenseCount.setText("3");
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
