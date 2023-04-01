package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.AvailableDate;

/**
 * An UI component that displays information of a {@code Volunteer}.
 */
public class VolunteerCard extends UiPart<Region> {

    private static final String FXML = "VolunteerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Volunteer volunteer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label nric;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label age;
    @FXML
    private Label email;
    @FXML
    private FlowPane region;
    @FXML
    private FlowPane medicalTags;
    @FXML
    private HBox tagsBox;
    @FXML
    private HBox medicalTagsBox;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox availableDatesBox;
    @FXML
    private HBox regionBox;
    @FXML
    private HBox addressBox;
    @FXML
    private HBox emailBox;
    @FXML
    private VBox phoneBox;
    @FXML
    private FlowPane availableDates;

    /**
     * Creates a {@code VolunteerCode} with the given {@code Volunteer} and index to display.
     *
     * @param volunteer      Volunteer to be displayed.
     * @param displayedIndex Index shown on screen.
     */
    public VolunteerCard(Volunteer volunteer, int displayedIndex) {
        super(FXML);
        this.volunteer = volunteer;
        id.setText(displayedIndex + ". ");
        name.setText(volunteer.getName().fullName);
        nric.setText(volunteer.getNric().value);
        phone.setText(volunteer.getPhone().value);
        address.setText(volunteer.getAddress().value);
        age.setText(String.valueOf(volunteer.getBirthDate().getAge()));
        email.setText(volunteer.getEmail().value);
        region.getChildren().add(
                new Label(volunteer.getRegion().region.name())
        );
        volunteer.getMedicalTags()
                .forEach(tag -> medicalTags.getChildren().add(new Label(tag.toFullString())));
        volunteer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        volunteer.getAvailableDates().stream()
                .sorted(Comparator.comparing(AvailableDate::getStartDate))
                .forEach(availableDate -> availableDates.getChildren().add(new Label(availableDate.toString())));

        if (volunteer.getTags().isEmpty()) {
            tagsBox.getChildren().removeAll(tagsBox.getChildren());
        }
        if (volunteer.getMedicalTags().isEmpty()) {
            medicalTagsBox.getChildren().removeAll(medicalTagsBox.getChildren());
        }
        if (volunteer.getRegion().toString().isEmpty()) {
            regionBox.getChildren().removeAll(regionBox.getChildren());
        }
        if (volunteer.getAvailableDates().isEmpty()) {
            availableDatesBox.getChildren().removeAll(availableDatesBox.getChildren());
        }
        if (volunteer.getAddress().toString().isEmpty()) {
            addressBox.getChildren().removeAll(addressBox.getChildren());
        }
        if (volunteer.getEmail().toString().isEmpty()) {
            emailBox.getChildren().removeAll(emailBox.getChildren());
        }
        if (volunteer.getPhone().toString().isEmpty()) {
            phoneBox.getChildren().removeAll(phoneBox.getChildren());
        }
        if (displayedIndex == 0) {
            id.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VolunteerCard)) {
            return false;
        }

        // state check
        VolunteerCard card = (VolunteerCard) other;
        return id.getText().equals(card.id.getText())
                && volunteer.equals(card.volunteer);
    }
}
