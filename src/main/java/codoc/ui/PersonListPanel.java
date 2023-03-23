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

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(MainWindow mainWindow) {
        super(FXML);
        ObservableList<Person> personList = mainWindow.getLogic().getFilteredPersonList();
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell(mainWindow));
        personListView.setSelectionModel(null);
        personListView.setOnMousePressed(event -> {
            System.out.println("hi");
            try {
                int index = personListView.getSelectionModel().getSelectedIndex();
                // do something with selected person
            } catch (NullPointerException e) {
                // do nothing
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        private MainWindow mainWindow;
        public PersonListViewCell(MainWindow mainWindow) {
            this.mainWindow = mainWindow;
        }

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(mainWindow, person, getIndex() + 1).getRoot());
            }
        }
    }

}
