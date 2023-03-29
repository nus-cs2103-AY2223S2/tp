package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * The loading section that shows a loading screen before showing the MainWindow
 *
 * @author Haiqel Bin Hanaffi
 */
public class LoadingSection extends UiPart<Region> {
    private static final String FXML = "LoadingSection.fxml";

    public LoadingSection() {
        super(FXML);
    }
}
