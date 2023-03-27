package mycelium.mycelium.ui.statisticsbox;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.UiPart;


/**
 * An UI component that displays {@code Project} statistics.
 */
public class StatisticsBox extends UiPart<Region> {
    private static final String FXML = "StatisticsBox.fxml";
    private StatisticsPanel statisticsPanel;

    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private StackPane statisticsPanelPlaceholder;
    @FXML
    private PieChart progressOverview;


    /**
     * Creates a {@code StatisticsBox} with the given {@code }.
     */
    public StatisticsBox(Logic logic) {
        super(FXML);
        loadDataOnBox(logic);

        assert pieChartData.isEmpty() == false;
        logger.fine("Finished initialising Statistics Box");
    }


    private void loadDataOnBox(Logic logic) {
        ObservableList<Project> filteredProjectList = logic.getFilteredProjectList();

        filteredProjectList.addListener(new ListChangeListener<Project>() {
            @Override
            public void onChanged(Change<? extends Project> c) {
                statisticsPanel.updateTabMessages(logic);
                addPieChartData(logic);
            }
        });

        addPieChartData(logic);
        progressOverview.setData(pieChartData);

        addProjectData(logic);
    }

    private void addProjectData(Logic logic) {
        statisticsPanel = new StatisticsPanel(logic.getDueProjectList(), logic.getOverdueProjectList());
        statisticsPanelPlaceholder.getChildren().add(statisticsPanel.getRoot());
        statisticsPanel.updateTabMessages(logic);
    }

    private void addPieChartData(Logic logic) {
        pieChartData.clear();

        if (logic.getFilteredProjectList().size() != 0) {
            logic.getProjectStatistics().forEach((k, v) -> {
                if (v != 0) {
                    pieChartData.add(new PieChart.Data(k, v));
                }
            });
        }
    }
}
