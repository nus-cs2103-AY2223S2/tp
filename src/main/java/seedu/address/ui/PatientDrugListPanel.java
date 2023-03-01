package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.drug.Drug;
import seedu.address.model.person.Person;
import seedu.address.model.person.patient.Patient;

/**
 * Panel containing the list of persons.
 */
public class PatientDrugListPanel extends UiPart<Region> {
    private static final String FXML = "PatientDrugListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientDrugListPanel.class);

    @FXML
    private ListView<Patient> patientListView;
    @FXML
    private ListView<Drug> drugListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PatientDrugListPanel(ObservableList<Patient> patientList, ObservableList<Drug> drugList) {
        super(FXML);

        // PERSON LIST
        patientListView.setItems(patientList);
//        drugListView.setCellFactory(listView -> new PatientListViewCell());

        // DRUG LIST
        drugListView.setItems(drugList);
//        drugListView.setCellFactory(listView -> new DrugListViewCell());
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
