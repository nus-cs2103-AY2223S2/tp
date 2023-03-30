package vimification.ui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * An UI component that displays the manual page for Vimification.
 */
public class ManualPanel extends UiPart<VBox> {

    private static final String FXML = "ManualPanel.fxml";

    @FXML
    private WebView webView;

    public ManualPanel() {
        super(FXML);
        // TODO: Change to Path
        File manualHtml = new File("./src/main/resources/view/ManualPanel.html");
        System.out.println(manualHtml.exists());
        WebEngine webEngine = webView.getEngine();
        webEngine.load(manualHtml.toURI().toString());

        webView.prefWidthProperty().bind(this.getRoot().widthProperty());
        webView.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

}
