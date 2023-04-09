package vimification.ui;

import java.net.URISyntaxException;
import java.nio.file.Path;

import javafx.fxml.FXML;
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
        Path manualHtml = Path.of(FXML_FILE_FOLDER, "HelpManualPanel.html");
        System.out.println(manualHtml.toString());
        WebEngine webEngine = webView.getEngine();
        webEngine.load(manualHtml.toString());
        String manualHtmlPath;
        try {
            manualHtmlPath =
                    getClass().getResource(FXML_FILE_FOLDER + "HelpManualPanel.html").toURI()
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
