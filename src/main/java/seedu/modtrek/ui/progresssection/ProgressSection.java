package seedu.modtrek.ui.progresssection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.modtrek.ui.UiPart;

public class ProgressSection extends UiPart<Region> {
    private static final String FXML = "progresssection/ProgressSection.fxml";

    @FXML
    private StackPane progressSection;

    public ProgressSection() {
        super(FXML);
        progressSection.getChildren().add(new DoughnutChart(createDoughnutData()));

    }

    private ObservableList<PieChart.Data> createDoughnutData() {
        return FXCollections.observableArrayList(
                new PieChart.Data("CSF_complete", 24),
                new PieChart.Data("CSF_incomplete", 12),

                new PieChart.Data("MS_complete", 8),
                new PieChart.Data("MS_incomplete", 8),

                new PieChart.Data("UE_complete", 28),
                new PieChart.Data("UE_incomplete", 12),

                new PieChart.Data("ITP_complete", 1),
                new PieChart.Data("ITP_incomplete", 12),

                new PieChart.Data("ULR_complete", 16),
                new PieChart.Data("ULR_incomplete", 0),

                new PieChart.Data("CSBD_complete", 1),
                new PieChart.Data("CSBD_incomplete", 40));
    }
}
