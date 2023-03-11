package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.InternshipApplication;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<InternshipApplication> applicationListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<InternshipApplication> applicationList) {
        super(FXML);
        applicationListView.setItems(applicationList);
        applicationListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<InternshipApplication> {
        @Override
        protected void updateItem(InternshipApplication application, boolean empty) {
            super.updateItem(application, empty);

            if (empty || application == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(application, getIndex() + 1).getRoot());
            }
        }
    }

}
