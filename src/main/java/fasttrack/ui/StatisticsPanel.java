package fasttrack.ui;

import fasttrack.model.AnalyticModel;
import fasttrack.model.util.AnalyticsType;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

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
    private Label budgetAdvice;
    @FXML
    private HBox weeklyChangeBackground;
    @FXML
    private HBox monthlyChangeBackground;

    /**
     * Creates a new StatisticsPanel object with the specified AnalyticModel object
     * and binds all values to the statistics
     * @param analyticModel the AnalyticModel object for data retrieval
     */
    public StatisticsPanel(AnalyticModel analyticModel) {
        super(FXML);
        this.analyticModel = analyticModel;
        bindAllValuesToStatistics();
        updateBudgetAdvice(analyticModel.getMonthlySpent().doubleValue());
        analyticModel.getMonthlySpent().addListener((observable, oldValue, newValue) -> {
            updateBudgetAdvice(newValue);
        });
        analyticModel.getMonthlyBudgetProperty().addListener((observable, oldValue, newValue) -> {
            updateBudgetAdvice(analyticModel.getMonthlySpent().get());
        });
    }

    /**
     * Binds the given AnalyticsType value to the given Label object
     * and formats the label text as a price or percentage
     * @param analyticsType the AnalyticsType value to bind
     * @param labelToBind   the Label object to bind the value to
     * @param isPrice       true if the value should be formatted as a price, false if to be formatted as a percentage
     */
    private void bindValueToStatistic(AnalyticsType analyticsType, Label labelToBind, boolean isPrice) {
        String formatString = isPrice ? "$%.2f" : "%.2f%%";
        labelToBind.textProperty().bind(analyticModel.getAnalyticsData(analyticsType).asString(formatString));
    }

    /**
     * Binds the given AnalyticsType value to the given Label object
     * and updates the percentage change indicator style based on the value
     * @param analyticsType the AnalyticsType value to bind
     */
    private void bindValueToChangeIndicator(AnalyticsType analyticsType) {
        DoubleProperty changeValue = analyticModel.getAnalyticsData(analyticsType);
        if (analyticsType != AnalyticsType.WEEKLY_CHANGE
            && analyticsType != AnalyticsType.MONTHLY_CHANGE) {
            throw new IllegalArgumentException(
                "The change indicator only accepts MONTHLY_CHANGE or WEEKLY_CHANGE!");
        }
        // Updating weekly change indicator
        if (analyticsType == AnalyticsType.WEEKLY_CHANGE) {
            weeklyChange.textProperty().bind(changeValue.asString("%.2f%%"));
            updateChangeIndicatorStyles(
                weeklyChange, weeklySign, weeklyChangeBackground, changeValue.doubleValue());
            changeValue.addListener((observable, oldValue, newValue) -> {
                updateChangeIndicatorStyles(
                    weeklyChange, weeklySign, weeklyChangeBackground, newValue.doubleValue());
            });
        } else {
            // Updating monthly change indicator
            monthlyChange.textProperty().bind(changeValue.asString("%.2f%%"));
            updateChangeIndicatorStyles(
                monthlyChange, monthlySign, monthlyChangeBackground, changeValue.doubleValue());
            changeValue.addListener((observable, oldValue, newValue) -> {
                updateChangeIndicatorStyles(
                    monthlyChange, monthlySign, monthlyChangeBackground, newValue.doubleValue());
            });
        }
    }

    private void updateChangeIndicatorStyles(Label labelToUpdate,
                                             Label signToUpdate,
                                             HBox backgroundToUpdate, double value) {
        // Determine the CSS classes to apply based on the value of the change
        String textColorClass = (value > 0)
                ? "negative_change_indicator"
                : "positive_change_indicator";
        String backgroundColorClass = (value > 0)
                ? "change_indicator_background_negative"
                : "change_indicator_background_positive";
        // Add sign label with a plus if the value is positive
        signToUpdate.setText((value >= 0) ? "+" : "");
        // Update the style classes with new background
        signToUpdate.getStyleClass().removeAll("negative_change_indicator",
                "positive_change_indicator");
        signToUpdate.getStyleClass().add(textColorClass);
        backgroundToUpdate.getStyleClass()
                .removeAll("change_indicator_background_positive",
                        "change_indicator_background_negative");
        backgroundToUpdate.getStyleClass().add(backgroundColorClass);
        // Update the main labels showing the values
        labelToUpdate.getStyleClass()
                .removeAll("negative_change_indicator",
                        "positive_change_indicator");
        labelToUpdate.getStyleClass().add(textColorClass);
    }

    private void bindAllValuesToStatistics() {
        bindValueToStatistic(AnalyticsType.MONTHLY_SPENT, monthlySpending, true);
        bindValueToStatistic(AnalyticsType.MONTHLY_REMAINING, monthlyRemaining, true);
        bindValueToStatistic(AnalyticsType.WEEKLY_SPENT, weeklySpending, true);
        bindValueToStatistic(AnalyticsType.WEEKLY_REMAINING, weeklyRemaining, true);
        bindValueToStatistic(AnalyticsType.TOTAL_SPENT, totalSpending, true);
        bindValueToStatistic(AnalyticsType.BUDGET_PERCENTAGE, budgetPercentage, false);
        bindValueToChangeIndicator(AnalyticsType.WEEKLY_CHANGE);
        bindValueToChangeIndicator(AnalyticsType.MONTHLY_CHANGE);
    }

    /**
     * Updates the budget advice text based on the new value of the monthly spent.
     * @param newValue the new value of the monthly spent
     */
    private void updateBudgetAdvice(Number newValue) {
        String adviceText = "Great job! You are within your budget!";
        if (analyticModel.getBudget() != 0 && newValue.doubleValue() > analyticModel.getBudget()) {
            adviceText = "You have exceeded your monthly budget!";
        }
        budgetAdvice.setText(adviceText);
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
