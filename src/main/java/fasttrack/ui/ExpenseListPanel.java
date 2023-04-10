package fasttrack.ui;

import java.util.logging.Logger;

import fasttrack.commons.core.LogsCenter;
import fasttrack.model.expense.Expense;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of expenses.
 */
public class ExpenseListPanel extends UiPart<Region> {
    private static final String FXML = "ExpenseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpenseListPanel.class);

    @FXML
    private ListView<Expense> expenseListView;

    /**
     * Creates a {@code ExpenseListPanel} with the given {@code ObservableList}.
     */
    public ExpenseListPanel(ObservableList<Expense> expenseList) {
        super(FXML);
        expenseListView.setItems(expenseList);
        expenseListView.setCellFactory(listView -> new ExpenseListViewCell());
    }

    /**
     * Refreshes the list of expenses and its related data.
     */
    public void refreshList() {
        expenseListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Expense} using a {@code ExpenseCard}.
     */
    class ExpenseListViewCell extends ListCell<Expense> {
        @Override
        protected void updateItem(Expense expense, boolean empty) {
            super.updateItem(expense, empty);

            if (empty || expense == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpenseCard(expense, getIndex() + 1).getRoot());
            }
        }
    }

}
