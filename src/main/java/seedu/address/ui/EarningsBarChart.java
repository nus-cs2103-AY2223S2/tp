package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import seedu.address.model.session.Session;

/**
 * Constructs a barchart for earnings the past 6 months
 */
public class EarningsBarChart extends BarChart<String, Number> {
    private static final int NUM_MONTHS = 6;

    /**
     * @param sessionList sessions of the coach
     */
    public EarningsBarChart(ObservableList<Session> sessionList) {
        super(new CategoryAxis(), new NumberAxis());

        // Set the title of the chart
        setTitle("Monthly Earnings (past " + NUM_MONTHS + " months)");

        // Set the labels for the X and Y axes
        getXAxis().setLabel("Month");
        getYAxis().setLabel("Earnings");

        // Set the data for the chart
        setData(getChartData(sessionList));
    }

    private ObservableList<Series<String, Number>> getChartData(ObservableList<Session> sessionList) {
        // Create an observable list of series for the chart
        ObservableList<Series<String, Number>> data = FXCollections.observableArrayList();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Iterate through the past NUM_MONTHS months and add the earnings for each month to the data list
        for (int i = 0; i < NUM_MONTHS; i++) {
            // Calculate the start and end dates for the current month
            LocalDate startDate = currentDate.withDayOfMonth(1).minus(i, ChronoUnit.MONTHS);
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);

            // Calculate the earnings for the current month
            float earnings = getMonthlyEarnings(sessionList, startDate, endDate);

            // Create a series for the current month and add it to the data list
            Series<String, Number> series = new Series<>();
            series.getData().add(new Data<>(startDate.format(DateTimeFormatter.ofPattern("MMM yy")), earnings));
            data.add(series);
        }

        return data;
    }


    private float getMonthlyEarnings(ObservableList<Session> sessionList, LocalDate startDate, LocalDate endDate) {
        float totalEarnings = 0;

        for (Session session : sessionList) {
            LocalDate sessionDate = LocalDate.parse(session
                            .getStartDateTime().split(" ")[0],
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (sessionDate.isEqual(startDate)
                    || (sessionDate.isAfter(startDate) && sessionDate.isBefore(endDate.plusDays(1)))) {
                totalEarnings += session.getTotalPay();
            }
        }

        return totalEarnings;
    }
}
