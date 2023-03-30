package codoc.ui;

import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.model.person.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private MainWindow.ClickListener clickListener;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(MainWindow mainWindow) {
        super(FXML);
        ObservableList<Person> personList = mainWindow.getLogic().getFilteredPersonList();
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    public void showLastItem() {
        personListView.scrollTo(personListView.getItems().size() - 1);
    }

    public void showIndex(int index) {
        personListView.scrollTo(index);
        personListView.getSelectionModel().select(index);
    }

    /**
     * Set UiEventListener for InfoTab.
     * @param listener
     */
    public void setClickListener(MainWindow.ClickListener listener) {
        this.clickListener = listener;
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
                PersonCard item = new PersonCard(person, getIndex() + 1);
                item.setClickListener(clickListener);
                setGraphic(item.getRoot());
            }
        }
    }

}
