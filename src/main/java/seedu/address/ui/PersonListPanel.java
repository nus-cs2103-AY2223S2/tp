package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tutee.Tutee;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Tutee> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Tutee> tuteeList) {
        super(FXML);
        personListView.setItems(tuteeList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tutee} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Tutee> {
        @Override
        protected void updateItem(Tutee tutee, boolean empty) {
            super.updateItem(tutee, empty);

            if (empty || tutee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(tutee, getIndex() + 1).getRoot());
            }
        }
    }

}
