package arb.ui.project;

import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.model.project.Project;
import arb.ui.UiPart;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of projects.
 */
public class ProjectListPanel extends UiPart<Region> {
    private static final String FXML = "project/ProjectListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProjectListPanel.class);

    @FXML
    private ListView<Project> projectListView;

    /**
     * Creates a {@code ProjectListPanel} with the given {@code ObservableList}.
     */
    public ProjectListPanel(ObservableList<Project> projectList) {
        super(FXML);
        projectListView.setItems(projectList);
        projectListView.setCellFactory(listView -> new ProjectListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Project} using a {@code ProjectCard}.
     */
    class ProjectListViewCell extends ListCell<Project> {
        @Override
        protected void updateItem(Project project, boolean empty) {
            super.updateItem(project, empty);

            if (empty || project == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProjectCard(project, getIndex() + 1).getRoot());
            }
        }
    }

}
