package fasttrack.ui;

import fasttrack.model.category.Category;
import fasttrack.model.util.UserInterfaceUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

/**
 * A UI component that displays information of a {@code Expense} or {@code Category}
 * with a title and the category filter applied if any.
 */
public class ResultsHeader extends UiPart<Region> {

    private static final String FXML = "ResultsHeader.fxml";

    @FXML
    private Label resultType;
    @FXML
    private Label filterType;

    /**
     * Creates a {@code ResultsHeader} with the given {@code categoryFilter}.
     */
    public ResultsHeader(SimpleObjectProperty<Category> categoryFilter) {
        super(FXML);
        filterType.textProperty().bindBidirectional(categoryFilter, new CategoryStringConverter());
        resultType.setText("Expenses");
    }

    /**
     * Sets the header of the results pane based on the given screen type.
     * @param screenType the type of screen to set the header for
     */
    public void setHeader(ScreenType screenType) {
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            resultType.setText("Expenses");
            return;
        } else if (screenType == ScreenType.CATEGORY_SCREEN) {
            resultType.setText("Category");
        } else if (screenType == ScreenType.RECURRING_EXPENSE_SCREEN) {
            resultType.setText("Recurring Expenses");
        }
        filterType.setText("");
    }

    /**
     * A custom string converter that converts a Category object to its capitalized category name,
     * or "All" if the category is null.
     * This is used to display the currently selected category filter, which can be null if no
     * category filter is selected
     */
    public static class CategoryStringConverter extends StringConverter<Category> {
        @Override
        public String toString(Category category) {
            if (category != null) {
                return UserInterfaceUtil.capitalizeFirstLetter(category.getCategoryName());
            }
            return "All";
        }
        /**
         * Not implemented for this class, always returns null.
         * @param string the string to convert
         * @return null
         */
        @Override
        public Category fromString(String string) {
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResultsHeader)) {
            return false;
        }

        // state check
        ResultsHeader header = (ResultsHeader) other;
        return resultType.getText().equals(header.resultType.getText())
                && filterType.getText().equals(header.filterType.getText());
    }
}
