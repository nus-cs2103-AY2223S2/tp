package mycelium.mycelium.ui.resultoutput;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import mycelium.mycelium.ui.UiPart;

/**
 * The ui for the page of a given tab.
 */
public class TabPage extends UiPart<Tab> {
    private static final String FXML = "TabPage.fxml";

    @FXML
    private StackPane contentPlaceholder;

    /**
     * Initialises a {@code TabPage} with a given title.
     *
     * @param title   Title of the Tab
     * @param content Content the tab should be poulated with
     */
    public TabPage(String title, UiPart<? extends Node> content) {
        super(FXML);
        getRoot().setText(title);
        contentPlaceholder.getChildren().add(content.getRoot());
    }
}
