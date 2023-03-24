package mycelium.mycelium.ui.statisticsbox;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
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
    private EntityList<Project> overdueProjectPanel;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private StackPane pies;
    @FXML
    private StackPane listView;

    @FXML
    private StackPane overdueListView;
    @FXML
    private PieChart progressOverview;
    @FXML
    private StackPane messageBox;
    @FXML
    private StackPane overdueMessageBox;
    @FXML
    private Label noDueProjectLabel = new Label("No due projects");
    @FXML
    private Label noOverdueProjectLabel = new Label("No overdue projects");


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
        // To do: change variable name
        ObservableList<Project> filteredProjectList = logic.getFilteredProjectList();

        filteredProjectList.addListener(new ListChangeListener<Project>() {
            @Override
            public void onChanged(Change<? extends Project> c) {
                addPieChartData(logic);
            }
        });

        addPieChartData(logic);
        progressOverview.setData(pieChartData);

        addProjectData(logic.getDueProjectList(), messageBox, noDueProjectLabel, listView,
                dueProjectPanel, false);

        addProjectData(logic.getOverdueProjectList(), overdueMessageBox, noOverdueProjectLabel, overdueListView,
                overdueProjectPanel, false);
    }

    private void addProjectData(ObservableList<Project> projects, StackPane displayBox, Label message,
                                StackPane view, EntityList<Project> panel, boolean wasLoaded) {
        if (!wasLoaded) {
            displayBox.getChildren().add(message);

            panel = new EntityList<Project>(projects, SpecialProjectEntity::new);
            view.getChildren().addAll(panel.getRoot());
            logger.fine("Initialised list view panel with " + projects.size() + " items");
        } else {
            panel.setItems(projects);
            logger.fine("Modified list view panel with " + projects.size() + " items");
        }

        if (projects.size() == 0) {
            message.setVisible(true);
        } else {
            message.setVisible(false);
        }
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
