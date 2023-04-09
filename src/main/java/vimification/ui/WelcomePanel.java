package vimification.ui;

import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * An UI component that displays the manual page for Vimification.
 */
public class WelcomePanel extends UiPart<VBox> {

    private static final String FXML = "WelcomePanel.fxml";

    @FXML
    private WebView webView;

    /**
     * Constructor for WelcomePanel.
     */
    public WelcomePanel() {
        super(FXML);
        WebEngine webEngine = webView.getEngine();
        String manualHtmlPath;
        try {
            manualHtmlPath = getClass().getResource(FXML_FILE_FOLDER + "WelcomePanel.html").toURI()
                    .toString();
            webEngine.load(manualHtmlPath);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        webView.prefWidthProperty().bind(this.getRoot().widthProperty());
        webView.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

}
