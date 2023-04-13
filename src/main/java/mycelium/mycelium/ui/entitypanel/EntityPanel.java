package mycelium.mycelium.ui.entitypanel;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.ui.UiPart;
import mycelium.mycelium.ui.utils.TabsPanel;

/**
 * The ui for the holder of all the tabs.
 */
public class EntityPanel extends UiPart<TabPane> implements TabsPanel {
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
        clientTab = new EntityTab("Clients", clientListPanel);
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
        case "Projects":
            this.projectListPanel.nextItem();
            break;
        case "Clients":
            this.clientListPanel.nextItem();
            break;
        default:
            break;
        }
    }

    @Override
    public void prevItem() {
        Tab tab = this.selectionModel.getSelectedItem();
        switch (tab.getText()) {
        case "Projects":
            this.projectListPanel.prevItem();
            break;
        case "Clients":
            this.clientListPanel.prevItem();
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
     * The identifier for projects will be the project name.
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
        case "Clients":
            return Optional.ofNullable(this.clientListPanel.getSelectedItem())
                .map(Client::getEmail)
                .map(Email::toString);
        default:
            return Optional.empty();
        }
    }
}
