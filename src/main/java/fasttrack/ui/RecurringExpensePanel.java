package fasttrack.ui;

import fasttrack.model.expense.RecurringExpenseManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


/**
 * Panel containing the list of recurring expenses.
 */
public class RecurringExpensePanel extends UiPart<Region> {
    private static final String FXML = "RecurringExpenseListPanel.fxml";

    @FXML
    private ListView<RecurringExpenseManager> recurringExpenseListView;

    /**
     * Creates a {@code RecurringExpensePanel} with the given {@code ObservableList}.
     */
    public RecurringExpensePanel(ObservableList<RecurringExpenseManager> recurringExpenseList) {
        super(FXML);
        recurringExpenseListView.setItems(recurringExpenseList);
        recurringExpenseListView.setCellFactory(listView -> new RecurringExpenseListViewCell());
    }

    /**
     * Refreshes the list of recurring expenses and its related data.
     */
    public void refreshList() {
        recurringExpenseListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code RecurringExpenseManager}
     * using a {@code RecurringExpenseCard}.
     */
    class RecurringExpenseListViewCell extends ListCell<RecurringExpenseManager> {
        @Override
        protected void updateItem(RecurringExpenseManager expense, boolean empty) {
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
