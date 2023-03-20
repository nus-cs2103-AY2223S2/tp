package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.AnalyticModel;

/**
 * The UI component that is responsible for displaying user spending statistics
 */
public class StatisticsPanel extends UiPart<Region> {

    private static final String FXML = "StatisticsPanel.fxml";
    private final AnalyticModel analyticModel;

    @FXML
    private Label monthlySpending;
    @FXML
    private Label weeklySpending;
    @FXML
    private Label monthlyRemaining;
    @FXML
    private Label weeklyRemaining;
    @FXML
    private Label monthlyChange;
    @FXML
    private Label weeklyChange;
    @FXML
    private Label totalSpending;
    @FXML
    private Label budgetPercentage;


    public StatisticsPanel(AnalyticModel analyticModel) {
        super(FXML);
        this.analyticModel = analyticModel;
        monthlySpending.setText("$50");
        monthlyRemaining.setText("$340");
        weeklySpending.setText("$3430");
        weeklyRemaining.setText("$3475");

        weeklyChange.setText("$+375");
        monthlyChange.setText("-$5475");

        totalSpending.setText("3475");
        budgetPercentage.setText("347%");
    }

    public void setDetails(int numResults, String timeFilter, boolean isExpense) {

    }

//    @Override
//    public boolean equals(Object other) {
//        // short circuit if same object
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof ResultsDetails)) {
//            return false;
//        }
//
//        // state check
//        ResultsDetails details = (ResultsDetails) other;
//        return resultsCount.getText().equals(details.resultsCount.getText())
//                && dateFilter.getText().equals(details.dateFilter.getText());
//    }

}
