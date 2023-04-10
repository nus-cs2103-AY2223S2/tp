package vimification.ui;

import java.net.URISyntaxException;
import java.nio.file.Path;
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
public class HelpManualPanel extends UiPart<VBox> {

    private static final Logger LOGGER = LogsCenter.getLogger(HelpManualPanel.class);
    private static final String FXML = "HelpManualPanel.fxml";

    @FXML
    private WebView webView;

    /**
     * Constructor for HelpManualPanel.
     */
    public HelpManualPanel() {
        super(FXML);
        Path manualHtml = Path.of(FXML_FILE_FOLDER, "HelpManualPanel.html");
        LOGGER.info(manualHtml.toString());
        WebEngine webEngine = webView.getEngine();
        webEngine.load(manualHtml.toString());
        String manualHtmlPath;
        try {
            manualHtmlPath = getClass()
                    .getResource(FXML_FILE_FOLDER + "HelpManualPanel.html").toURI()
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
