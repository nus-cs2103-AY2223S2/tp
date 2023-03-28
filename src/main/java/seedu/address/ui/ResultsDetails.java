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
    //TODO add new constructor parameter with RecurringExpense List
    public ResultsDetails(ObservableList<Expense> expenseList, ObservableList<Category> categoryList) {
        super(FXML);
        this.count = new SimpleIntegerProperty();
        this.expenseList = expenseList;
        this.categoryList = categoryList;
        bindResultsCount(ScreenType.EXPENSE_SCREEN);
        dateLabel.setText("Date:");
        dateFilter.setText("All");
    }


    /**
     * Switches the details displayed on the GUI based on a time filter and a boolean flag indicating
     * whether expenses or categories is being displayed.
     * @param timeFilter a string representing the time filter to be applied to the details.
     * @param screenType an enum indicating which screen is being displayed.
     */
    public void switchDetails(String timeFilter, ScreenType screenType) {
        bindResultsCount(screenType);
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            dateFilter.setText(timeFilter);
            dateLabel.setText("Date:");
        } else if (screenType == ScreenType.CATEGORY_SCREEN || screenType == ScreenType.RECURRING_EXPENSE_SCREEN){
            dateFilter.setText("");
            dateLabel.setText("");
        }
    }

    /**
     * Helper method used by the switchDetails method to bind the count of the number of items
     * in the expenseList or categoryList to the count variable,
     * which is then displayed in the GUI.
     * @param screenType an enum indicating which screen is being displayed.
     */
    private void bindResultsCount(ScreenType screenType) {
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            IntegerBinding expenseListSizeBinding = Bindings.size(expenseList);
            count.bind(expenseListSizeBinding);
        } else if (screenType == ScreenType.CATEGORY_SCREEN){
            IntegerBinding categoryListSizeBinding = Bindings.size(categoryList);
            count.bind(categoryListSizeBinding);
        } else if (screenType == ScreenType.RECURRING_EXPENSE_SCREEN) {
            //TODO add new RecurringExpense
            IntegerBinding recurringExpenseListSizeBinding = Bindings.size(expenseList);
            count.bind(recurringExpenseListSizeBinding);
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
