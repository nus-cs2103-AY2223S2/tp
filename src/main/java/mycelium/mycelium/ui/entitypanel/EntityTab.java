package mycelium.mycelium.ui.entitypanel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.UiPart;

/**
 * The ui for the page of a given tab.
 */
public class EntityTab extends UiPart<Tab> {
    private static final String FXML = "EntityTab.fxml";
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private StackPane entityListPlaceholder;

    /**
     * Initialises a {@code TabPage} with a given title.
     *
     * @param title   Title of the Tab
     * @param content Content the tab should be populated with
     */
    public EntityTab(String title, UiPart<? extends Node> content) {
        super(FXML);
        getRoot().setText(title);
        entityListPlaceholder.getChildren().add(content.getRoot());
        logger.fine("Initialized tab page with title: " + title);
    }
}
