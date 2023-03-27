package mycelium.mycelium.ui.statisticsbox;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.UiPart;

import java.util.logging.Logger;

/**
 * The ui for the page of a given tab in Statistics Dashboard.
 */
public class StatisticsTab extends UiPart<Tab> {
    private static final String FXML = "StatisticsTab.fxml";
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private StackPane listPlaceholder;

    @FXML
    private StackPane displayMessageBox;

    /**
     * Initialises a {@code TabPage} in Statistics Dashboard with a given title.
     *
     * @param title   Title of the Tab
     * @param content Content the tab should be populated with
     * @param message Message that the tab will display when content is empty
     */
    public StatisticsTab(String title, UiPart<? extends Node> content, Label message) {
        super(FXML);
        getRoot().setText(title);
        displayMessageBox.getChildren().add(message);
        listPlaceholder.getChildren().addAll(content.getRoot());
        logger.fine("Initialized tab page with title: " + title);
    }
}
