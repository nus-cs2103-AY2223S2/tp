package seedu.socket.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.socket.commons.core.LogsCenter;
import seedu.socket.logic.Logic;
import seedu.socket.model.project.Project;

/**
 * Panel containing the list of tasks.
 */
public class ProjectListPanel extends UiPart<Region> {
    private static final String FXML = "ProjectListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProjectListPanel.class);
    private Logic logic;
    @FXML
    private ListView<Project> projectListView;

    /**
     * Creates a {@code ProjectListPanel} with the given {@code ObservableList}.
     */
    public ProjectListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        projectListView.setFocusTraversable(false);
        projectListView.setItems(logic.getFilteredProjectList());
        projectListView.setCellFactory(listView -> new ProjectListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ProjectListViewCell extends ListCell<Project> {
        @Override
        protected void updateItem(Project project, boolean empty) {
            super.updateItem(project, empty);
            setMouseTransparent(true);
            if (empty || project == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProjectCard(project, getIndex() + 1).getRoot());
            }
        }
    }
}
