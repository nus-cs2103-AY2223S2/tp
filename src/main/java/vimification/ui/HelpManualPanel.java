package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * An UI component that displays the manual page for Vimification.
 */
public class HelpManualPanel extends UiPart<VBox> {

    private static final String FXML = "HelpManualPanel.fxml";

    @FXML
    private WebView webView;

    /**
     * Constructor for HelpManualPanel.
     */
    public HelpManualPanel() {
        super(FXML);
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://ay2223s2-cs2103t-t15-3.github.io/tp/UserGuide.html");
        webView.prefWidthProperty().bind(this.getRoot().widthProperty());
        webView.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

    public boolean equals(Node obj) {
        return getRoot().equals(obj);
    }
}
