package seedu.careflow.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.careflow.model.hospital.Hospital;

/**
 * A UI component that displays information of a {@code HospitalHotline}.
 */
public class HospitalCard extends UiPart<Region> {
    private static final String FXML = "HospitalCard.fxml";

    public final Hospital hospital;

    @FXML
    private HBox cardPane;
    @FXML
    private Label hospitalName;

    @FXML
    private Label hotline;

    /**
     * Creates a {@code HospitalCode} with the given {@code Hospital} and index to display.
     */
    public HospitalCard(Hospital hospital) {
        super(FXML);
        this.hospital = hospital;
        this.hospitalName.setText(this.hospital.getHospitalName());
        this.hotline.setText(this.hospital.getHotline());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles null
        if (!(other instanceof HospitalCard)) {
            return false;
        }

        HospitalCard card = (HospitalCard) other;
        return this.hospitalName.getText().equals(card.hospitalName.getText())
                && this.hospital.equals(card.hospital);
    }
}


