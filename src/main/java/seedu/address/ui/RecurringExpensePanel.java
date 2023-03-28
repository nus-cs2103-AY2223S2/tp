package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.expense.Expense;

import java.util.logging.Logger;

/**
 * Panel containing the list of expenses.
 */
public class RecurringExpensePanel extends UiPart<Region> {
    private static final String FXML = "RecurringExpenseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecurringExpensePanel.class);

    @FXML
    private ListView<Expense> recurringExpenseListView;

    /**
     * Creates a {@code ExpenseListPanel} with the given {@code ObservableList}.
     */
    public RecurringExpensePanel(ObservableList<Expense> expenseList) {
        super(FXML);
        recurringExpenseListView.setItems(expenseList);
        recurringExpenseListView.setCellFactory(listView -> new RecurringExpenseListViewCell());
    }

    /**
     * Refreshes the list of recurring expenses and its related data.
     */
    public void refreshList() {
        recurringExpenseListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Expense} using a {@code ExpenseCard}.
     */
    class RecurringExpenseListViewCell extends ListCell<Expense> {
        @Override
        protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);

            if (empty || expense == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecurringExpenseCard(expense, getIndex() + 1).getRoot());
            }
        }
    }

}
