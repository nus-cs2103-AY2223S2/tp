package seedu.internship.ui.pages;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.internship.Datapoint;
import seedu.internship.model.internship.Statistics;

/**
 * A page to display statistical information about internship applications.
 */
public class StatsPage extends Page {
    private static final String FXML = "StatsPage.fxml";

    private static final String PAGE_TITLE = "Statistics";

    private final Logger logger = LogsCenter.getLogger(StatsPage.class);

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

    /**
     * Creates a {@code StatsPage} based on given Statistics
     *
     * @param statistics Calculated statistics
     */
    public StatsPage(Statistics statistics) {
        super(FXML);
        this.statistics = statistics;
        setHeadContent();
        setBodyContent();
    }

    /**
     * Fills in the content for the page's title and subtitle.
     */
    private void setHeadContent() {
        pageTitle.setText(PAGE_TITLE);
        chartInfo.setText(statistics.getTotalInternships().getNameValue());

    }

    /**
     * Sets the content for the body of HomePage.
     * The body of HomePage contains the Events Reminder segment and the Useful Commands segment.
     */
    private void setBodyContent() {
        buildPieChart();
        StringBuilder summary = new StringBuilder();
        for (Datapoint datapoint : statistics.getAllDatapoints()) {
            summary.append(datapoint.getNameValue());
            summary.append("\n");
        }

        statsSummary.setText(summary.toString());
    }

    /**
     * Fills in the details of the pie chart element.
     */
    private void buildPieChart() {
        pieChart.setData(pieCharDataFrom(statistics.getNumInterested(),
                statistics.getNumApplied(),
                statistics.getNumOffered(),
                statistics.getNumRejected()));
        pieChart.setTitle("Internship Application Progress");
    }

    /**
     * Creates a list of {@code PieChart.Data} from given {@code Datapoint}.
     */
    private ObservableList<PieChart.Data> pieCharDataFrom(Datapoint ... datapoints) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Datapoint datapoint : datapoints) {
            pieChartData.add(new PieChart.Data(datapoint.getNameValue(), datapoint.getValue()));
        }
        return pieChartData;
    }

}
