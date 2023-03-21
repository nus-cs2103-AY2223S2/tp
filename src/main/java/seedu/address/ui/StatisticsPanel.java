package seedu.address.ui;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.AnalyticModel;
import seedu.address.model.util.AnalyticsType;

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
    @FXML
    private Label monthlySign;
    @FXML
    private Label weeklySign;
    @FXML
    private HBox weeklyChangeBackground;
    @FXML
    private HBox monthlyChangeBackground;

    public StatisticsPanel(AnalyticModel analyticModel) {
        super(FXML);
        this.analyticModel = analyticModel;
        bindAllValuesToStatistics();
    }

    private void bindValueToStatistic(AnalyticsType analyticsType, Label labelToBind, boolean isPrice) {
        String formatString = isPrice ? "$%.2f" : "%.2f%%";
        labelToBind.textProperty().bind(analyticModel.getAnalyticsData(analyticsType).asString(formatString));
    }

    private void bindValueToChangeIndicator(AnalyticsType analyticsType, Label labelToBind) {

        DoubleProperty changeValue = analyticModel.getAnalyticsData(analyticsType);
        labelToBind.textProperty().bind(changeValue.asString("%.2f%%"));

        if (analyticsType != AnalyticsType.WEEKLY_CHANGE && analyticsType != AnalyticsType.MONTHLY_CHANGE) {
            throw new IllegalArgumentException("The change indicator only accepts MONTHLY_CHANGE or WEEKLY_CHANGE!");
        }

        updateChangeIndicatorStyles(analyticsType, labelToBind, changeValue.doubleValue());

        changeValue.addListener((observable, oldValue, newValue) -> {
            updateChangeIndicatorStyles(analyticsType, labelToBind, newValue.doubleValue());
        });

    }

    private void updateChangeIndicatorStyles(AnalyticsType analyticsType, Label labelToUpdate, double value) {

        String textColorClass = (value >= 0) ? "negative_change_indicator" : "positive_change_indicator";
        String backgroundColorClass = (value >= 0) ? "change_indicator_background_negative" : "change_indicator_background_positive";

        if (analyticsType == AnalyticsType.WEEKLY_CHANGE) {
            if (value >= 0) {
                weeklySign.setText("+");
            } else {
                weeklySign.setText("");
            }
            weeklySign.getStyleClass().removeAll("negative_change_indicator", "positive_change_indicator");
            weeklySign.getStyleClass().add(textColorClass);
            weeklyChangeBackground.getStyleClass().removeAll("change_indicator_background_positive", "change_indicator_background_negative");
            weeklyChangeBackground.getStyleClass().add(backgroundColorClass);
        } else if (analyticsType == AnalyticsType.MONTHLY_CHANGE) {
            if (value >= 0) {
                monthlySign.setText("+");
            } else {
                monthlySign.setText("");
            }
            monthlySign.getStyleClass().removeAll("negative_change_indicator", "positive_change_indicator");
            monthlySign.getStyleClass().add(textColorClass);
            monthlyChangeBackground.getStyleClass().removeAll("change_indicator_background_positive", "change_indicator_background_negative");
            monthlyChangeBackground.getStyleClass().add(backgroundColorClass);
        }

        labelToUpdate.getStyleClass().removeAll("negative_change_indicator", "positive_change_indicator");
        labelToUpdate.getStyleClass().add(textColorClass);
    }


    private void bindAllValuesToStatistics() {
        bindValueToStatistic(AnalyticsType.MONTHLY_SPENT, monthlySpending, true);
        bindValueToStatistic(AnalyticsType.MONTHLY_REMAINING, monthlyRemaining, true);
        bindValueToStatistic(AnalyticsType.WEEKLY_SPENT, weeklySpending, true);
        bindValueToStatistic(AnalyticsType.WEEKLY_REMAINING, weeklyRemaining, true);
        bindValueToStatistic(AnalyticsType.TOTAL_SPENT, totalSpending, true);
        bindValueToStatistic(AnalyticsType.BUDGET_PERCENTAGE, budgetPercentage, false);
        bindValueToChangeIndicator(AnalyticsType.WEEKLY_CHANGE, weeklyChange);
        bindValueToChangeIndicator(AnalyticsType.MONTHLY_CHANGE, monthlyChange);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatisticsPanel)) {
            return false;
        }

        // state check
        StatisticsPanel statistics = (StatisticsPanel) other;
        return monthlySpending.getText().equals(statistics.monthlySpending.getText())
                && monthlyRemaining.getText().equals(statistics.monthlyRemaining.getText())
                && weeklySpending.getText().equals(statistics.weeklySpending.getText())
                && weeklyRemaining.getText().equals(statistics.weeklyRemaining.getText())
                && weeklyChange.getText().equals(statistics.weeklyChange.getText())
                && monthlyChange.getText().equals(statistics.monthlyChange.getText())
                && totalSpending.getText().equals(statistics.totalSpending.getText())
                && budgetPercentage.getText().equals(statistics.budgetPercentage.getText());
    }
}
