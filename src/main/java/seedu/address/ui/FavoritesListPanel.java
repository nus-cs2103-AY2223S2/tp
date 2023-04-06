package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class FavoritesListPanel extends UiPart<Region> {
    private static final String FXML = "FavoritesListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FavoritesListPanel.class);
    private final ObservableList<Person> filteredPersonList;
    @FXML
    private ListView<Person> favoritesListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FavoritesListPanel(ObservableList<Person> favoritesList, ObservableList<Person> filteredPersonList) {
        super(FXML);
        this.filteredPersonList = filteredPersonList;
        filteredPersonList.addListener((ListChangeListener<Person>) change -> favoritesListView.refresh());

        favoritesListView.setItems(favoritesList);
        favoritesListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                int filteredListPersonIndex = filteredPersonList.indexOf(person);
                setGraphic(new PersonCard(person, filteredListPersonIndex + 1).getRoot());
            }
        }
    }

}
