package seedu.dengue.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dengue.logic.analyst.DataBin;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DataBinCard extends UiPart<Region> {

    private static final String FXML = "DataBinListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on DengueHotspotTracker level 4</a>
     */

    public final DataBin dataBin;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label binName;
    @FXML
    private Label binSize;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DataBinCard(DataBin dataBin, int displayedIndex) {
        super(FXML);
        this.dataBin = dataBin;
        id.setText(displayedIndex + ". ");
        binName.setText(dataBin.getName());
        binSize.setText(String.valueOf(dataBin.getSize()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DataBinCard)) {
            return false;
        }

        // state check
        DataBinCard card = (DataBinCard) other;
        return id.getText().equals(card.id.getText())
                && dataBin.equals(card.dataBin);
    }
}
