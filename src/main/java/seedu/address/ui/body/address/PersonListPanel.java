package seedu.address.ui.body.address;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "body/address/PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;
    @FXML
    private Label count;

    private Person selectedPerson;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, PersonDetailPanel panel) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setFocusTraversable(false);
        personListView.setOnMouseClicked(event -> {
            Person clickedPerson = personListView.getSelectionModel().getSelectedItem();
            if (Objects.equals(clickedPerson, selectedPerson)) {
                // the same Person was clicked on
                clearSelection();
            } else {
                selectedPerson = clickedPerson;
            }
        });

        /* Updates PersonDetailPanel accordingly
         * when the selected Person and index changes.
         */
        MultipleSelectionModel<Person> model = personListView.getSelectionModel();
        model.selectedItemProperty().addListener((observable, oldValue, newValue) -> panel.setPerson(newValue));
        model.selectedIndexProperty().addListener((observable, oldValue, newValue) -> panel
                .setDisplayedIndex(newValue.intValue() + 1));

        count.textProperty().bind(getCountProperty(personList));
    }

    /**
     * Scrolls the {@code ListView} of {@code Person}s to the top.
     */
    public void scrollToTop() {
        personListView.scrollTo(0);
    }

    /**
     * Clears the selection in the {@code ListView}.
     */
    public void clearSelection() {
        personListView.getSelectionModel().clearSelection();
        selectedPerson = null;
    }

    private StringBinding getCountProperty(ObservableList<Person> list) {
        return Bindings.createStringBinding(() -> String.format("%d contact(s)", list.size()), list);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                if (getIndex() == 0) {
                    getStyleClass().add("first-cell");
                } else {
                    getStyleClass().remove("first-cell");
                }
            }
        }
    }
}
