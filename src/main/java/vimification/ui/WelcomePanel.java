package vimification.ui;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import vimification.common.core.LogsCenter;
import vimification.common.util.StringUtil;

/**
 * An UI component that displays the manual page for Vimification.
 */
public class WelcomePanel extends UiPart<VBox> {

    private static final Logger LOGGER = LogsCenter.getLogger(WelcomePanel.class);

    private static final String FXML = "WelcomePanel.fxml";

    @FXML
    private WebView webView;

    /**
     * Constructor for WelcomePanel.
     */
    public WelcomePanel() {
        super(FXML);
        WebEngine webEngine = webView.getEngine();
        try {
            String manualHtmlPath =
                    getClass().getResource(FXML_FILE_FOLDER + "WelcomePanel.html").toURI()
                            .toString();
            webEngine.load(manualHtmlPath);
        } catch (URISyntaxException ex) {
            LOGGER.info(StringUtil.getDetails(ex));
        }

        webView.prefWidthProperty().bind(this.getRoot().widthProperty());
        webView.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

    public boolean equals(Node obj) {
        return getRoot().equals(obj);
    }

}
