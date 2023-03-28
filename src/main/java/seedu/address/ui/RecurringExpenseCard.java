package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.expense.Expense;
import seedu.address.model.util.UserInterfaceUtil;

/**
 * A UI component that displays information of a {@code Expense}.
 */
public class RecurringExpenseCard extends UiPart<Region> {

    private static final String FXML = "RecurringExpenseListCard.fxml";

    public final Expense expense;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label expenseName;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private Label dateRange;
    @FXML
    private Label frequency;

    /**
     * Creates a {@code ExpenseCard} with the given {@code Expense} and index to display.
     */
    public RecurringExpenseCard(Expense expense, int displayedIndex) {
        super(FXML);
        this.expense = expense;
        id.setText(displayedIndex + ". ");
        expenseName.setText(UserInterfaceUtil.capitalizeFirstLetter(expense.getName()));
        String categoryName = expense.getCategory().getCategoryName();
        category.setText(UserInterfaceUtil.capitalizeFirstLetter(categoryName));
        dateRange.setText(UserInterfaceUtil.parseDate(expense.getDate()) + " - " + UserInterfaceUtil.parseDate(expense.getDate()));
        price.setText(UserInterfaceUtil.parsePrice(expense.getAmount()));
        frequency.setText("Weekly");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecurringExpenseCard)) {
            return false;
        }

        // state check
        RecurringExpenseCard card = (RecurringExpenseCard) other;
        return id.getText().equals(card.id.getText())
                && expense.equals(card.expense);
    }
}
