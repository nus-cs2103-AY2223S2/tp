package mycelium.mycelium.ui.statisticsbox;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.ui.UiPart;
import mycelium.mycelium.ui.entitypanel.EntityList;
import mycelium.mycelium.ui.utils.TabsPanel;


/**
 * The ui for the holder of all the tabs in Statistics Dashboard.
 */
public class StatisticsPanel extends UiPart<TabPane> implements TabsPanel {
    private static final String FXML = "EntityPanel.fxml";
    private static final String NO_DUE_PROJECT_MESSAGE = "No projects due soon";
    private static final String NO_OVERDUE_PROJECT_MESSAGE = "No overdue projects!";

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
    public StatisticsPanel(ObservableList<Project> dueSoonProjectList,
                           ObservableList<Project> overdueProjectList) {
        super(FXML);
        dueSoonProjectListPanel = new EntityList<Project>(dueSoonProjectList, SpecialProjectEntity::new);
        overdueProjectListPanel = new EntityList<Project>(overdueProjectList, SpecialProjectEntity::new);
        dueSoonProjectTab = new StatisticsTab("Due soon", dueSoonProjectListPanel);
        overdueProjectTab = new StatisticsTab("Overdue", overdueProjectListPanel);
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

    @Override
    public void nextTab() {
        int size = this.tabs.size();
        if (size == 0) {
            return;
        }
        assert size > 0;
        int i = this.selectionModel.getSelectedIndex();
        this.selectionModel.select((i + 1) % size);
    }

    @Override
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

    @Override
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

    @Override
    public void highlight() {
        ObservableList<String> styleClass = getRoot().getStyleClass();

        if (styleClass.contains(HIGHLIGHTED_STYLE_CLASS)) {
            return;
        }

        styleClass.add(HIGHLIGHTED_STYLE_CLASS);
    }

    @Override
    public void unhighlight() {
        getRoot().getStyleClass().remove(HIGHLIGHTED_STYLE_CLASS);
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

    /**
     * Updates the statistics panel when there is a change
     * in the due soon and overdue projects.
     *
     * @param logic the logic component.
     */
    public void onChanged(Logic logic) {
        if (logic.getDueProjectList().size() == 0) {
            dueSoonProjectTab.showMessage(NO_DUE_PROJECT_MESSAGE);
        } else {
            dueSoonProjectTab.hideMessage();
        }

        if (logic.getOverdueProjectList().size() == 0) {
            overdueProjectTab.showMessage(NO_OVERDUE_PROJECT_MESSAGE);
        } else {
            overdueProjectTab.hideMessage();
        }
    }
}

