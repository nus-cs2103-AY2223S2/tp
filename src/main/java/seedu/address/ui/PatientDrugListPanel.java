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
import seedu.address.model.person.Patient;

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
//        patientListView.setCellFactory(listView -> new PatientListViewCell());

        // DRUG LIST
        drugListView.setItems(drugList);
//        drugListView.setCellFactory(listView -> new DrugListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PersonListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Drug} using a {@code DrugCard}.
     */
    class DrugListViewCell extends ListCell<Drug> {
        @Override
        protected void updateItem(Drug drug, boolean empty) {
            super.updateItem(drug, empty);

            if (empty || drug == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DrugCard(drug, getIndex() + 1).getRoot());
            }
        }
    }
}
