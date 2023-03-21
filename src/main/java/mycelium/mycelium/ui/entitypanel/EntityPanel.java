package mycelium.mycelium.ui.entitypanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.UiPart;

/**
 * The ui for the holder of all the tabs.
 */
public class EntityPanel extends UiPart<TabPane> {
    private static final String FXML = "EntityPanel.fxml";
    private Logger logger = LogsCenter.getLogger(getClass());
    private EntityList<Client> clientListPanel;
    private EntityList<Project> projectListPanel;
    private EntityTab projectTab;
    private EntityTab clientTab;
    private SingleSelectionModel<Tab> selectionModel;
    private ObservableList<Tab> tabs;

    /**
     * Initialises a {@code EntityPanel} with a given {@code Logic}.
     *
     * @param logic Logic to be used by the EntityPanel
     */
    public EntityPanel(Logic logic) {
        super(FXML);
        projectListPanel = new EntityList<Project>(logic.getFilteredProjectList(), ProjectEntity::new);
        clientListPanel = new EntityList<Client>(logic.getFilteredClientList(), ClientEntity::new);
        projectTab = new EntityTab("Projects", projectListPanel);
        clientTab = new EntityTab("Client", clientListPanel);
        this.selectionModel = getRoot().getSelectionModel();
        this.tabs = getRoot().getTabs();
        this.tabs.add(projectTab.getRoot());
        this.tabs.add(clientTab.getRoot());
        logger.fine("Initialised EntityPanel");
    }

    public Tab getCurrentTab() {
        return this.selectionModel.getSelectedItem();
    }

    public void selectClientTab() {
        this.selectionModel.select(this.clientTab.getRoot());
    }

    public void selectProjectTab() {
        this.selectionModel.select(this.projectTab.getRoot());
    }
}
