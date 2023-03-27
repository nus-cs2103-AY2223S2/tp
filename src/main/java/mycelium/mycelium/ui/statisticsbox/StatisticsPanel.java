package mycelium.mycelium.ui.statisticsbox;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.ui.UiPart;
import mycelium.mycelium.ui.entitypanel.EntityList;


/**
 * The ui for the holder of all the tabs in Statistics Dashboard.
 */
public class StatisticsPanel extends UiPart<TabPane> {
    private static final String FXML = "EntityPanel.fxml";
    private Logger logger = LogsCenter.getLogger(getClass());
    private EntityList<Project> dueSoonProjectListPanel;
    private EntityList<Project> overdueProjectListPanel;
    private StatisticsTab dueSoonProjectTab;
    private StatisticsTab overdueProjectTab;
    private SingleSelectionModel<Tab> selectionModel;
    private ObservableList<Tab> tabs;


    /**
     * Initialises a {@code StatisticsPanel} with given {@code Logic} and message {@code Label}.
     */
    public StatisticsPanel(ObservableList<Project> dueSoonProjectList, Label noDueProjectLabel,
                           ObservableList<Project> overdueProjectList, Label noOverdueProjectLabel) {
        super(FXML);
        dueSoonProjectListPanel = new EntityList<Project>(dueSoonProjectList, SpecialProjectEntity::new);
        overdueProjectListPanel = new EntityList<Project>(overdueProjectList, SpecialProjectEntity::new);
        dueSoonProjectTab = new StatisticsTab("Due soon", dueSoonProjectListPanel, noDueProjectLabel);
        overdueProjectTab = new StatisticsTab("Overdue", overdueProjectListPanel, noOverdueProjectLabel);
        this.selectionModel = getRoot().getSelectionModel();
        this.tabs = getRoot().getTabs();
        this.tabs.add(dueSoonProjectTab.getRoot());
        this.tabs.add(overdueProjectTab.getRoot());
        logger.fine("Initialised StatisticsPanel");
    }

    /**
     * Selects the Due soon Project tab.
     */
    public void selectDueSoonProjectTab() {
        this.selectionModel.select(this.dueSoonProjectTab.getRoot());
    }

    /**
     * Selects the Overdue Project tab.
     */
    public void selectOverdueProjectTab() {
        this.selectionModel.select(this.overdueProjectTab.getRoot());
    }

    /**
     * Switches to the next tab.
     */
    public void nextTab() {
        int size = this.tabs.size();
        int i = this.selectionModel.getSelectedIndex();
        this.selectionModel.select((i + 1) % size);
    }

    /**
     * Selects and scroll to the next item in the current tab.
     * If there is no next item, it will select and scroll to the first item.
     */
    public void nextItem() {
        Tab item = this.selectionModel.getSelectedItem();
        switch (item.getText()) {
        case "Due soon":
            this.dueSoonProjectListPanel.nextItem();
            break;
        case "Overdue":
            this.overdueProjectListPanel.nextItem();
            break;
        default:
            break;
        }
    }

    /**
     * Selects and scroll to the previous item in the current tab.
     * If there is no previous item, it will select and scroll to the last item.
     */
    public void prevItem() {
        Tab tab = this.selectionModel.getSelectedItem();
        switch (tab.getText()) {
        case "Due soon":
            this.dueSoonProjectListPanel.prevItem();
            break;
        case "Overdue":
            this.overdueProjectListPanel.prevItem();
            break;
        default:
            break;
        }
    }

    /**
     * Sets the projects in the due soon project list panel.
     */
    public void setDueSoonProjects(ObservableList<Project> projects) {
        dueSoonProjectListPanel.setItems(projects);
    }

    /**
     * Sets the projects in the overdue project list panel.
     */
    public void setOverdueProjects(ObservableList<Project> projects) {
        overdueProjectListPanel.setItems(projects);
    }


    /**
     * Returns the selected entity identifier.
     * If there is no selected entity, it will return an empty optional.
     * The identifier for projects will be the project name.
     *
     * @return the selected entity identifier.
     */
    public Optional<String> getSelectedEntityIdentifier() {
        Tab tab = this.selectionModel.getSelectedItem();
        EntityList<Project> currentPanel = tab.getText().equals("Overdue")
                ? this.overdueProjectListPanel
                : this.dueSoonProjectListPanel;

        return Optional.ofNullable(currentPanel.getSelectedItem())
                .map(Project::getName)
                .map(NonEmptyString::toString);
    }
}

