package mycelium.mycelium.ui.common;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

/**
 * The ui for the page of a given tab.
 */
public class TabPage extends UiPart<Tab> {
    private static final String FXML = "TabPage.fxml";
    private UiPart<? extends Node> content;

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
        this.content = content;
    }

    /**
     * Populate the content of the Tab.
     */
    public void fillInnerContent() {
        contentPlaceholder.getChildren().add(content.getRoot());
    }
}
