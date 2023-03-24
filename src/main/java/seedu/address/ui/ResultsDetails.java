package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * A UI component that displays information of a {@code Expense} or {@code Category}
 * such as the number of results and the filter applied if any.
 */
public class ResultsDetails extends UiPart<Region> {

    private static final String FXML = "ResultsDetails.fxml";
    private final IntegerProperty count;
    private final ObservableList<Expense> expenseList;
    private final ObservableList<Category> categoryList;

    @FXML
    private Label resultsCount;
    @FXML
    private Label dateFilter;
    @FXML
    private Label dateLabel;

    /**
     * Creates a {@code ResultDetails} with the given details of the data in the Expense or Category list
     */
    public ResultsDetails(ObservableList<Expense> expenseList, ObservableList<Category> categoryList) {
        super(FXML);
        this.count = new SimpleIntegerProperty();
        this.expenseList = expenseList;
        this.categoryList = categoryList;
        bindResultsCount(true);
        dateLabel.setText("Date:");
        dateFilter.setText("All");
    }


    /**
     * Switches the details displayed on the GUI based on a time filter and a boolean flag indicating
     * whether expenses or categories is being displayed.
     * @param timeFilter a string representing the time filter to be applied to the details.
     * @param isExpenseDisplay a boolean flag indicating whether expenses or categories is being displayed.
     */
    public void switchDetails(String timeFilter, boolean isExpenseDisplay) {
        bindResultsCount(isExpenseDisplay);
        if (isExpenseDisplay) {
            dateFilter.setText(timeFilter);
            dateLabel.setText("Date:");
        } else {
            dateFilter.setText("");
            dateLabel.setText("");
        }
    }

    /**
     * Helper method used by the switchDetails method to bind the count of the number of items
     * in the expenseList or categoryList to the count variable,
     * which is then displayed in the GUI.
     * @param isExpenseDisplay a boolean flag indicating whether expenses or categories is being displayed.
     */
    private void bindResultsCount(boolean isExpenseDisplay) {
        if (isExpenseDisplay) {
            IntegerBinding expenseListSizeBinding = Bindings.size(expenseList);
            count.bind(expenseListSizeBinding);
        } else {
            IntegerBinding categoryListSizeBinding = Bindings.size(categoryList);
            count.bind(categoryListSizeBinding);
        }
        resultsCount.textProperty().bind(count.asString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResultsDetails)) {
            return false;
        }

        // state check
        ResultsDetails details = (ResultsDetails) other;
        return resultsCount.getText().equals(details.resultsCount.getText())
                && dateFilter.getText().equals(details.dateFilter.getText());
    }
}
