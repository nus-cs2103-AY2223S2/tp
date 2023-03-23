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
import mycelium.mycelium.ui.entitypanel.EntityList;

/**
 * An UI component that displays {@code Project} statistics.
 */
public class StatisticsBox extends UiPart<Region> {
    private static final String FXML = "StatisticsBox.fxml";
    private EntityList<Project> dueProjectPanel;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private StackPane pies;
    @FXML
    private StackPane listView;
    @FXML
    private PieChart deadlinePieChart;


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
        ObservableList<Project> lst = logic.getFilteredProjectList();

        lst.addListener(new ListChangeListener<Project>() {
            @Override
            public void onChanged(Change<? extends Project> c) {
                addPieChartData(logic);
                dueProjectPanel.setItems(logic.getDueProjectList());
            }
        });

        addPieChartData(logic);
        deadlinePieChart.setData(pieChartData);

        dueProjectPanel = new EntityList<Project>(logic.getDueProjectList(), DueProjectEntity::new);
        listView.getChildren().addAll(dueProjectPanel.getRoot());
        logger.fine("Initialised list view panel with " + logic.getDueProjectList().size() + " items");
    }


    private void addPieChartData(Logic logic) {
        pieChartData.clear();

        if (logic.getFilteredProjectList().size() != 0) {
            logic.getProjectStatistics().forEach((k, v) -> {
                pieChartData.add(new PieChart.Data(k, v));
            });
        }
    }

}
