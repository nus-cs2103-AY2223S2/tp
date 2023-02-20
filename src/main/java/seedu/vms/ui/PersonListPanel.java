package seedu.vms.ui;


import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.vms.model.IdData;
import seedu.vms.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final ObservableMap<Integer, IdData<Person>> personList;
    private final ObservableList<IdData<Person>> datas;

    @FXML
    private ListView<IdData<Person>> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableMap<Integer, IdData<Person>> dataMap) {
        super(FXML);
        this.personList = FXCollections.unmodifiableObservableMap(dataMap);
        datas = FXCollections.observableArrayList(dataMap.values());
        personListView.setItems(datas);
        personListView.setCellFactory(listView -> new PersonListViewCell());

        dataMap.addListener(this::handleChange);
    }

    private void handleChange(MapChangeListener.Change<? extends Integer, ? extends IdData<Person>> change) {
        datas.setAll(personList.values());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<IdData<Person>> {
        @Override
        protected void updateItem(IdData<Person> data, boolean empty) {
            super.updateItem(data, empty);

            if (empty || data == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(data.getValue(), getIndex() + 1).getRoot());
            }
        }
    }

}
