package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing a list of meetups
 */
public class ScheduledMeetsListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduledMeetsListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduledMeetsListPanel.class);

    @FXML
    private ListView<Person> scheduledMeetsListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ScheduledMeetsListPanel(ObservableList<Person> personList) {
        super(FXML);
        scheduledMeetsListView.setItems(personList);
        scheduledMeetsListView.setCellFactory(listView -> new ScheduledMeetsListPanel.ScheduledMeetsListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ScheduledMeetsListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person).getRoot());
            }
        }
    }

}
