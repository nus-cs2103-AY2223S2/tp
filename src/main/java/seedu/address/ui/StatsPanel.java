package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.InternshipApplication;

/**
 * Panel containing the list of persons.
 */
public class StatsPanel extends UiPart<Region> {
    private static final String FXML = "StatsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StatsPanel.class);

    @FXML
    private ListView<SimpleIntegerProperty> statsBoxesView;

    @FXML
    private HBox container;

    /**
     * Creates a {@code ViewContentPanel} with the given {@code ObservableList}.
     */
    public ApplicationListPanel(ObservableList<InternshipApplication> applicationList) {
        super(FXML);
        applicationListView.setItems(applicationList);
        applicationListView.setCellFactory(listView -> new ApplicationListViewCell());
    }

    public VBox getContainer() {
        return container;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code ApplicationCard}.
     */
    class ApplicationListViewCell extends ListCell<InternshipApplication> {
        @Override
        protected void updateItem(InternshipApplication application, boolean empty) {
            super.updateItem(application, empty);

            if (empty || application == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicationCard(application, getIndex() + 1).getRoot());
            }
        }
    }

}
