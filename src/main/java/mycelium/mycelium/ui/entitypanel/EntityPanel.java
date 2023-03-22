package mycelium.mycelium.ui.entitypanel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
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
     */
    public EntityPanel(ObservableList<Project> projectList, ObservableList<Client> clientList) {
        super(FXML);
        projectListPanel = new EntityList<Project>(projectList, ProjectEntity::new);
        clientListPanel = new EntityList<Client>(clientList, ClientEntity::new);
        projectTab = new EntityTab("Projects", projectListPanel);
        clientTab = new EntityTab("Client", clientListPanel);
        this.selectionModel = getRoot().getSelectionModel();
        this.tabs = getRoot().getTabs();
        this.tabs.add(projectTab.getRoot());
        this.tabs.add(clientTab.getRoot());
        logger.fine("Initialised EntityPanel");
    }

    /**
     * Selects the client tab.
     */
    public void selectClientTab() {
        this.selectionModel.select(this.clientTab.getRoot());
    }

    /**
     * Selects the project tab.
     */
    public void selectProjectTab() {
        this.selectionModel.select(this.projectTab.getRoot());
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
     * Sets the clients in the client list panel.
     */
    public void setClients(ObservableList<Client> list) {
        clientListPanel.setItems(list);
    }

    /**
     * Sets the projects in the project list panel.
     */
    public void setProjects(ObservableList<Project> list) {
        projectListPanel.setItems(list);
    }
}
