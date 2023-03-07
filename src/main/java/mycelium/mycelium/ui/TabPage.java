package mycelium.mycelium.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

/**
 * The ui for the page of a given tab.
 */
public class TabPage extends UiPart<Tab> {
    private static final String FXML = "TabPage.fxml";

    @FXML
    private StackPane contentPlaceholder;

    /**
     * Initialises a {@code TabPage} with a given title.
     * @param title
     */
    public TabPage(String title) {
        super(FXML);
        getRoot().setText(title);
    }

    /**
     * Populate the content of the Tab with a given root node.
     * @param root
     */
    void fillInnerContent(Node root) {
        contentPlaceholder.getChildren().add(root);
    }
}
