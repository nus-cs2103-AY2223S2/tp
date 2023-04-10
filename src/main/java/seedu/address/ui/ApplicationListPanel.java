package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.application.InternshipApplication;
import seedu.address.ui.popups.ControlBox;

/**
 * Panel containing the list of applications.
 */
public class ApplicationListPanel extends UiPart<Region> {
    private static final String FXML = "ApplicationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicationListPanel.class);

    private ViewContentPanel viewContentPanel;
    private InternshipApplication currentApplication;
    private ControlBox controlBox;
    private final MainWindow mainWindow;

    @FXML
    private ListView<InternshipApplication> applicationListView;

    @FXML
    private VBox container;

    /**
     * Creates a {@code ViewContentPanel} with the given {@code ObservableList}.
     */
    public ApplicationListPanel(ObservableList<InternshipApplication> applicationList, MainWindow mainWindow,
                                ViewContentPanel viewContentPanel) {
        super(FXML);
        this.viewContentPanel = viewContentPanel;
        this.mainWindow = mainWindow;
        controlBox = new ControlBox(mainWindow);
        container.getChildren().add(0, controlBox.getRoot());
        applicationListView.setItems(applicationList);
        applicationListView.setCellFactory(listView -> new ApplicationListViewCell());
    }

    /**
     * Handles mouse clicks for applicationListView to show the corresponding {@code InternshipApplication}
     * in the {@code ViewContentPanel}
     *
     * @param arg0 mouse click event
     */
    @FXML public void handleMouseClick(MouseEvent arg0) {
        InternshipApplication applicationSelected = applicationListView.getSelectionModel().getSelectedItem();
        this.currentApplication = applicationSelected;
        viewContentPanel.setInternshipApplication(applicationSelected);
    }

    public VBox getContainer() {
        return container;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code InternshipApplication}
     * using a {@code ApplicationCard}.
     */
    class ApplicationListViewCell extends ListCell<InternshipApplication> {
        @Override
        protected void updateItem(InternshipApplication application, boolean empty) {
            super.updateItem(application, empty);

            if (empty || application == null) {
                setGraphic(null);
                setText(null);
                viewContentPanel.clearPanel();
            } else {
                setGraphic(new ApplicationCard(application, getIndex() + 1, mainWindow).getRoot());
                if (currentApplication != null && application.isSameApplication(currentApplication)) {
                    viewContentPanel.setInternshipApplication(application);
                }
            }
        }
    }

}
