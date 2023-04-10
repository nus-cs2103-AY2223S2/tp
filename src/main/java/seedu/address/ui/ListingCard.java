package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ListingCard extends UiPart<Region> {

    private static final String FXML = "ListingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Listing listing;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label platformsHeader;
    @FXML
    private FlowPane platforms;
    @FXML
    private Label applicantCount;
    @FXML
    private FlowPane applicants;

    /**
     * Creates a {@code ListingCode} with the given {@code Listing} and index to display.
     */
    public ListingCard(Listing listing, int displayedIndex) {
        super(FXML);
        this.listing = listing;
        id.setText(displayedIndex + ". ");
        title.setText(listing.getTitle().fullTitle);
        applicantCount.setText("Number of applicants: " + listing.getApplicantCount());
        description.setText(listing.getDescription().fullDescription);
        if (listing.getPlatformCount() > 0) {
            platformsHeader.setText("Platforms released: " + listing.getPlatformCount());
        } else {
            platformsHeader.setText("No platforms released");
        }

        Stream<String> platformsDisplayed = listing.getPlatforms().stream()
                .map(platform -> platform.getPlatformName().fullPlatformName);
        platformsDisplayed.forEach(platformName -> platforms.getChildren().add(new Label(platformName)));


        Set<String> repeatedNames = getRepeatedNames(listing.getApplicants());
        Stream<String> displayedNames = getDisplayedNames(listing.getApplicants(), repeatedNames);

        displayedNames.forEach(name -> applicants.getChildren().add(new Label(name)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListingCard)) {
            return false;
        }

        // state check
        ListingCard card = (ListingCard) other;
        return id.getText().equals(card.id.getText())
                && listing.equals(card.listing);
    }

    private static Set<String> getRepeatedNames(ArrayList<Applicant> applicants) {
        HashSet<String> uniqueNames = new HashSet<>();
        HashSet<String> repeatedNames = new HashSet<>();
        for (Applicant applicant : applicants) {
            String name = applicant.getName().fullName;
            if (!uniqueNames.contains(name)) {
                uniqueNames.add(name);
            } else {
                repeatedNames.add(name);
            }
        }
        return repeatedNames;
    }

    private static Stream<String> getDisplayedNames(ArrayList<Applicant> applicants, Set<String> repeatedNames) {
        ArrayList<String> displayedNameList = new ArrayList<>();
        applicants.forEach(applicant -> {
            if (repeatedNames.contains(applicant.getName().fullName)) {
                displayedNameList.add(String.format("%s#%d", applicant.getName().fullName, applicant.hashCode()));
            } else {
                displayedNameList.add(applicant.getName().fullName);
            }
        });

        return displayedNameList.stream().sorted();
    }
}
