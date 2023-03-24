package mycelium.mycelium.ui.entitypanel;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
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
     * Selects and scroll to the next item in the current tab.
     * If there is no next item, it will select and scroll to the first item.
     */
    public void nextItem() {
        Tab item = this.selectionModel.getSelectedItem();
        switch (item.getText()) {
        case "Projects":
            this.projectListPanel.nextItem();
            break;
        case "Client":
            this.clientListPanel.nextItem();
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
        case "Projects":
            this.projectListPanel.prevItem();
            break;
        case "Client":
            this.clientListPanel.prevItem();
            break;
        default:
            break;
        }
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


    /**
     * Returns the selected entity identifier.
     * If there is no selected entity, it will return an empty optional.
     * The identifier for projects witll be the project name.
     * The identifier for clients will be the client email.
     *
     * @return the selected entity identifier.
     */
    public Optional<String> getSelectedEntityIdentifier() {
        Tab tab = this.selectionModel.getSelectedItem();
        switch (tab.getText()) {
        case "Projects":
            return Optional.ofNullable(this.projectListPanel.getSelectedItem())
                .map(Project::getName)
                .map(NonEmptyString::toString);
        case "Client":
            return Optional.ofNullable(this.clientListPanel.getSelectedItem())
                .map(Client::getEmail)
                .map(Email::toString);
        default:
            return Optional.empty();
        }
    }
}
