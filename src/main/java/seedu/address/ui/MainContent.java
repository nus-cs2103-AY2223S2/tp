package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * The Ui for Contenting both the list of fishes and tasks.
 */
//@@author AY2223S1-CS2103T-W15-1/tp-reused
//Reused from https://github.com/AY2223S1-CS2103T-W15-1/tp
// with minor modifications
public class MainContent extends UiPart<Region> {
    private static final String FXML = "MainContent.fxml";

    private final Logger logger = LogsCenter.getLogger(MainContent.class);

    @FXML
    private HBox mainContent;

    /**
     * Constructs a {@code MainContent} with the given {@code fishListPanel} and {@code taskListPanel}.
     */
    public MainContent(FishListPanel fishListPanel, TaskListPanel taskListPanel) {
        super(FXML);
        HBox.setHgrow(fishListPanel.getRoot(), Priority.ALWAYS);
        HBox.setHgrow(taskListPanel.getRoot(), Priority.ALWAYS);
        mainContent.getChildren().addAll(fishListPanel.getRoot(), taskListPanel.getRoot());
    };

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MainContent)) {
            return false;
        }

        // state check
        MainContent content = (MainContent) other;
        return mainContent.equals(content.mainContent);
    }

}
//@@author
