package seedu.address.ui;

import java.util.Comparator;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.internship.Internship;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    //Hashmap that stores the colours associated with each status
    private HashMap<String, Color> colorMap = new HashMap<String,Color>();



    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private Label statusLabel;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        id.setText(displayedIndex + ". ");
        companyName.setText(internship.getCompanyName().fullCompanyName);
        role.setText(internship.getRole().fullRole);
        status.setText(internship.getStatus().toString());
        date.setText(internship.getDate().fullDate);
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        //Set up status label
        setupColours();
        String statusString = internship.getStatus().toString();
        Color statusColor = colorMap.get(statusString);
        statusLabel.setText(statusString.toUpperCase());
        statusLabel.setBackground(new Background(new BackgroundFill(
                statusColor, new CornerRadii(10), new Insets(-5))));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }


    /**
     * Initialises the colours associated with the status label.
     */
    public void setupColours() {
        colorMap.put("New", Color.rgb(250, 155, 68, 1.0));
        colorMap.put("Applied", Color.rgb(68, 170, 250, 1.0));
        colorMap.put("Assessment", Color.rgb(250, 68, 155, 1.0));
        colorMap.put("Interview", Color.rgb(126, 68, 250, 1.0));
        colorMap.put("Offered", Color.rgb(42, 174, 79, 1.0));
        colorMap.put("Rejected", Color.rgb(250, 68, 68, 1.0));
    }
}
