package mycelium.mycelium.ui.statisticsbox;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.UiPart;


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

    @FXML
    private Label message;

    /**
     * Initialises a {@code TabPage} in Statistics Dashboard with a given title.
     *
     * @param title   Title of the Tab
     * @param content Content the tab should be populated with
     */
    public StatisticsTab(String title, UiPart<? extends Node> content) {
        super(FXML);
        getRoot().setText(title);
        listPlaceholder.getChildren().addAll(content.getRoot());
        logger.fine("Initialized tab page with title: " + title);
    }

    /**
     * Hides the message in the tab.
     */
    public void hideMessage() {
        displayMessageBox.setManaged(false);
    }

    /**
     * Shows a message in the tab.
     *
     * @param s Message to be shown
     */
    public void showMessage(String s) {
        message.setText(s);
        displayMessageBox.setManaged(true);
    }
}
