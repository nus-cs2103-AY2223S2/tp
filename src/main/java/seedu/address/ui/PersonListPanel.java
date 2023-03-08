package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.patient.Patient;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    @FXML
    private ListView<Patient> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        personListView.setItems(patientList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using
     * a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
