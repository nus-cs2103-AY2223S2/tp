package seedu.socket.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.socket.commons.core.LogsCenter;
import seedu.socket.logic.Logic;
import seedu.socket.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private Logic logic;
    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        personListView.setItems(logic.getFilteredPersonList());
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            personListView.setFocusTraversable(false);
            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                setOnMouseClicked(e -> {
                    logger.info("An item selected: " + PersonListViewCell.super.getItem().toString());
                    if (!logic.getViewedPerson().isEmpty()
                            && PersonListViewCell.super.getItem().isSamePerson(logic.getViewedPerson().get(0))) {
                        personListView.getSelectionModel().clearSelection();
                        logic.setViewedPerson(-1);
                    } else {
                        logic.setViewedPerson(getIndex());
                    }
                });
            }
        }
    }

}
