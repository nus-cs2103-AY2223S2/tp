package mycelium.mycelium.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;


public class TabPage extends UiPart<Tab> {
    private static final String FXML = "TabPage.fxml";

    @FXML
    private StackPane contentPlaceholder;

    public TabPage(String title) {
        super(FXML);
        getRoot().setText(title);
    }

    void fillInnerContent(Node root) {
        contentPlaceholder.getChildren().add(root);
    }
}