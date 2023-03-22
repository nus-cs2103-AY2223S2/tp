package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.logic.Logic;



/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;
    @FXML
    private Label nameCard;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label employeeId;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label department;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, Logic logic) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        //nameCard.setText("Welcome to ExecutivePro v1.2");
        setClick();
        logic.getFilteredPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                Person person = personListView.getSelectionModel().getSelectedItem();
                setInformation(person);
            }
        });


    }

    public void setClick() {
        personListView.setOnMouseClicked(mouseEvent -> {
            Person person = personListView.getSelectionModel().getSelectedItem();
            setInformation(person);
        });
    }
    public void setInformation(Person person) {
        name.setText("Name: " + person.getName().fullName);
        employeeId.setText("EmployeeId: " + person.getEmployeeId().value);
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        department.setText("Department: " + person.getDepartment().value);
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
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
