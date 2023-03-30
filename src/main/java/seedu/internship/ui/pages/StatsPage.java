package seedu.internship.ui.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.internship.Datapoint;
import seedu.internship.model.internship.Statistics;

import java.util.logging.Logger;

public class StatsPage extends Page {
    private static final String FXML = "StatsPage.fxml";
    private final Logger logger = LogsCenter.getLogger(StatsPage.class);
    private static final String PAGE_TITLE = "Statistics";

    private final Statistics statistics;

    @javafx.fxml.FXML
    private ScrollPane pageContainer;

    @FXML
    private Label pageTitle;

    @FXML
    private Label chartInfo;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label statsSummary;

    public StatsPage(Statistics statistics) {
        super(FXML);
        this.statistics = statistics;
        setHeadContent();
        setBodyContent();
    }

    private void setHeadContent() {
        pageTitle.setText(PAGE_TITLE);
        chartInfo.setText(statistics.getTotalInternships().getNameValue());

    }

    private void setBodyContent() {
        buildPieChart();
        StringBuilder summary = new StringBuilder();
        for (Datapoint datapoint : statistics.getAllDatapoints()) {
            summary.append(datapoint.getNameValue());
            summary.append("\n");
        }

        statsSummary.setText(summary.toString());
    }

    private void buildPieChart() {
        pieChart.setData(pieCharDataFrom(statistics.getNumInterested(),
                statistics.getNumApplied(),
                statistics.getNumOffered(),
                statistics.getNumRejected()));
        pieChart.setTitle("Internship Application Progress");
    }

    private ObservableList<PieChart.Data> pieCharDataFrom(Datapoint ... datapoints) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Datapoint datapoint : datapoints) {
            pieChartData.add(new PieChart.Data(datapoint.getNameValue(), datapoint.getValue()));
        }
        return pieChartData;
    }

}
