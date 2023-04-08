package fasttrack.ui;

import fasttrack.logic.parser.ParserUtil;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

/**
 * A UI component that displays information of a {@code Expense} or {@code Category}
 * such as the number of results and the time range filter applied.
 */
public class ResultsDetails extends UiPart<Region> {

    private static final String FXML = "ResultsDetails.fxml";
    private final IntegerProperty count;
    private final ObservableList<Expense> expenseList;
    private final ObservableList<Category> categoryList;
    private final ObservableList<RecurringExpenseManager> recurringExpenseManagersList;

    @FXML
    private Label resultsCount;
    @FXML
    private Label dateFilter;
    @FXML
    private Label dateLabel;

    /**
     * Creates a new ResultsDetails pane to display the details of each data
     * @param expenseList the list of expenses to display
     * @param recurringExpenseManagersList the list of recurring expense managers to display
     * @param categoryList the list of categories to display
     * @param timeFilter the filter to apply to expense based on month, week, year
     */
    public ResultsDetails(ObservableList<Expense> expenseList,
                          ObservableList<RecurringExpenseManager> recurringExpenseManagersList,
                          ObservableList<Category> categoryList,
                          SimpleObjectProperty<ParserUtil.Timespan> timeFilter) {
        super(FXML);
        this.count = new SimpleIntegerProperty();
        this.expenseList = expenseList;
        this.categoryList = categoryList;
        this.recurringExpenseManagersList = recurringExpenseManagersList;
        dateLabel.setText("Date:");
        bindResultsCount(ScreenType.EXPENSE_SCREEN);
        dateFilter.textProperty().bindBidirectional(timeFilter, new CustomStringConverter());
    }


    /**
     * Switches the details displayed on the GUI based on an enum indicating
     * which screen is being displayed.
     * @param screenType an enum indicating which screen is being displayed.
     */
    public void switchDetails(ScreenType screenType) {
        bindResultsCount(screenType);
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            dateLabel.setText("Time:");
        } else if (screenType == ScreenType.CATEGORY_SCREEN || screenType == ScreenType.RECURRING_EXPENSE_SCREEN) {
            dateLabel.setText("");
            dateFilter.setText("");
        }
    }

    /**
     * Helper method used by the switchDetails method to bind the count of the number of items
     * in the expenseList, categoryList or recurringExpenseManagerList to the count variable,
     * which is then displayed in the GUI.
     * @param screenType an enum indicating which screen is being displayed.
     */
    private void bindResultsCount(ScreenType screenType) {
        if (screenType == ScreenType.EXPENSE_SCREEN) {
            IntegerBinding expenseListSizeBinding = Bindings.size(expenseList);
            count.bind(expenseListSizeBinding);
        } else if (screenType == ScreenType.CATEGORY_SCREEN) {
            IntegerBinding categoryListSizeBinding = Bindings.size(categoryList);
            count.bind(categoryListSizeBinding);
        } else if (screenType == ScreenType.RECURRING_EXPENSE_SCREEN) {
            IntegerBinding recurringExpenseListSizeBinding = Bindings.size(recurringExpenseManagersList);
            count.bind(recurringExpenseListSizeBinding);
        }
        resultsCount.textProperty().bind(count.asString());
    }


    /**
     * A custom string converter for the date filter.
     */
    private static class CustomStringConverter extends StringConverter<ParserUtil.Timespan> {
        @Override
        public String toString(ParserUtil.Timespan myEnum) {
            return myEnum.toString();
        }
        /**
         * Not implemented for this class, always returns null.
         * @param string the string to convert
         * @return null
         */
        @Override
        public ParserUtil.Timespan fromString(String string) {
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
        if (!(other instanceof ResultsDetails)) {
            return false;
        }

        // state check
        ResultsDetails details = (ResultsDetails) other;
        return resultsCount.getText().equals(details.resultsCount.getText())
                && dateFilter.getText().equals(details.dateFilter.getText());
    }
}
