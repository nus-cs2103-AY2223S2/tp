package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.fish.Fish;

/**
 * An UI component that displays information of a {@code Fish}.
 */
public class FishCard extends UiPart<Region> {

    private static final String FXML = "FishListCard.fxml";
    private static final String NAME_HEADER = "Name: ";
    private static final String LAST_FED_DATE_HEADER = "Last fed on: ";
    private static final String SPECIES_HEADER = "Species: ";
    private static final String FEEDING_INTERVAL_HEADER = "Feeding interval: ";
    private static final String TANK_HEADER = "Tank: ";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Fish fish;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label lastFedDate;
    @FXML
    private Label species;
    @FXML
    private Label feedingInterval;
    @FXML
    private Label tank;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView fishImage;
    @FXML
    private Label test;

    /**
     * Creates a {@code FishCode} with the given {@code Fish} and index to display.
     */
    public FishCard(Fish fish, int displayedIndex) {
        super(FXML);
        this.fish = fish;
        id.setText(displayedIndex + ". ");
        String nameLabelToBeSet = fish.getName().fullName;
        name.setText(nameLabelToBeSet);
        String lastFedDateLabelToBeSet = LAST_FED_DATE_HEADER + fish.getLastFedDateTime().value;
        lastFedDate.setText(lastFedDateLabelToBeSet);
        String speciesLabelToBeSet = fish.getSpecies().species;
        tags.getChildren().add(new Label(speciesLabelToBeSet));
        String feedingIntervalLabelToBeSet = FEEDING_INTERVAL_HEADER + fish.getFeedingInterval().toString();
        feedingInterval.setText(feedingIntervalLabelToBeSet);
        String tankLabelToBeSet = fish.getTank().getTankName().fullTankName;
        tags.getChildren().add(new Label(tankLabelToBeSet));
        fish.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        Image image = new Image(getClass().getResourceAsStream(fish.getFishImage()));
        fishImage.setImage(image);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FishCard)) {
            return false;
        }

        // state check
        FishCard card = (FishCard) other;
        return id.getText().equals(card.id.getText())
                && fish.equals(card.fish);
    }
}
