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
import seedu.vms.model.patient.Patient;

/**
 * Panel containing the list of patients.
 */
public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    private final ObservableMap<Integer, IdData<Patient>> patientList;
    private final ObservableList<IdData<Patient>> datas;

    @FXML
    private ListView<IdData<Patient>> patientListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableMap<Integer, IdData<Patient>> dataMap) {
        super(FXML);
        this.patientList = FXCollections.unmodifiableObservableMap(dataMap);
        datas = FXCollections.observableArrayList(dataMap.values());
        patientListView.setItems(datas);
        patientListView.setCellFactory(listView -> new PatientListViewCell());

        dataMap.addListener(this::handleChange);
    }

    private void handleChange(MapChangeListener.Change<? extends Integer, ? extends IdData<Patient>> change) {
        datas.setAll(patientList.values());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PatientListViewCell extends ListCell<IdData<Patient>> {
        @Override
        protected void updateItem(IdData<Patient> data, boolean empty) {
            super.updateItem(data, empty);

            if (empty || data == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(data.getValue(), data.getId() + 1).getRoot());
            }
        }
    }

}
