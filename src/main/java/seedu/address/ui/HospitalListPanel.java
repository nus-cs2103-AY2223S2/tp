package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.fxml.FXML;
import seedu.address.model.hospital.Hospital;

public class HospitalListPanel extends UiPart<Region>{

    private static final String FXML = "HospitalListPanel.fxml";
    @FXML
    private ListView<Hospital> hospitalListView;

    public HospitalListPanel(ObservableList<Hospital> hospitalHotlineList) {
        super(FXML);
        this.hospitalListView.setItems(hospitalHotlineList);
        this.hospitalListView.setCellFactory(listView -> new HotlineListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Hospital} using a {@code HospitalCard}.
     */
    class HotlineListViewCell extends ListCell<Hospital> {
        @Override
        protected void updateItem(Hospital hospital, boolean empty) {
            super.updateItem(hospital, empty);
            if (empty || hospital == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HospitalCard(hospital).getRoot());
            }
        }
    }
}
