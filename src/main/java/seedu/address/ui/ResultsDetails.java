package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Expense} or {@code Category}
 * such as the number of results and the filter applied if any.
 */
public class ResultsDetails extends UiPart<Region> {

    private static final String FXML = "ResultsDetails.fxml";

    @FXML
    private Label resultsCount;
    @FXML
    private Label dateFilter;
    @FXML
    private Label dateLabel;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ResultsDetails(int numResults, String timeFilter, boolean isExpense) {
        super(FXML);
        resultsCount.setText(Integer.toString(numResults));
        dateLabel.setText("Date:");
        if (isExpense) {
            dateFilter.setText(timeFilter);
        } else {
            dateFilter.setText("");
            dateLabel.setText("");
        }
    }

    public void setDetails(int numResults, String timeFilter, boolean isExpense) {
        resultsCount.setText(Integer.toString(numResults));
        dateFilter.setText(timeFilter);
        if (isExpense) {
            dateFilter.setText(timeFilter);
            dateLabel.setText("Date:");
        } else {
            dateFilter.setText("");
            dateLabel.setText("");
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
