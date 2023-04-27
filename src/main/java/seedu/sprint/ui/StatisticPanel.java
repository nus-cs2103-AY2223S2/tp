package seedu.sprint.ui;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.sprint.commons.core.LogsCenter;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.statistic.Statistic;

/**
 * A UI component that displays the statistics for current applications.
 */
public class StatisticPanel extends UiPart<Region> {

    private static final String FXML = "StatisticPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StatisticPanel.class);
    private Statistic statistic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label totalNum;
    @FXML
    private Label interestedNum;
    @FXML
    private Label appliedNum;
    @FXML
    private Label offeredNum;
    @FXML
    private Label rejectedNum;
    @FXML
    private PieChart pieChart;

    /**
     * Constructs a new StatisticPanel containing the statistics
     * for current applications.
     *
     * @param applications an ObservableList containing current applications.
     */
    public StatisticPanel(ObservableList<Application> applications) {
        super(FXML);
        setNewStatistics(applications);
    }

    public void setNewStatistics(ObservableList<Application> applications) {
        requireNonNull(applications);
        this.statistic = new Statistic(applications);
        HashMap<Status, Integer> statsMap = statistic.getStatsMap();

        int numInterested = statsMap.get(new Status("interested"));
        int numApplied = statsMap.get(new Status("applied"));
        int numOffered = statsMap.get(new Status("offered"));
        int numRejected = statsMap.get(new Status("rejected"));

        totalNum.setText("Total Number of Internship Applications: " + statistic.getTotalNum().toString());
        interestedNum.setText(String.format("%d", numInterested));
        appliedNum.setText(String.format("%d", numApplied));
        offeredNum.setText(String.format("%d", numOffered));
        rejectedNum.setText(String.format("%d", numRejected));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Interested", numInterested),
                new PieChart.Data("Applied", numApplied),
                new PieChart.Data("Offered", numOffered),
                new PieChart.Data("Rejected", numRejected));

        pieChart.setData(pieChartData);
        pieChart.setTitle("Your Applications");
        pieChart.setClockwise(true);
        pieChart.setStartAngle(180);
        pieChart.setLegendVisible(false);
        pieChart.setLabelsVisible(false);
        applyCustomColorSequence(pieChartData, "#91d7ff", "#ffbd59", "#5bb288", "#ff8787");
    }

    //@@author XylusChen-reused
    //Reused from https://stackoverflow.com/questions/15219334/javafx-change-piechart-color
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }
    //@@author

}
