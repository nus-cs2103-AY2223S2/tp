package seedu.address.ui;

import static seedu.address.model.internship.Status.APPLIED;
import static seedu.address.model.internship.Status.ASSESSMENT;
import static seedu.address.model.internship.Status.INTERVIEW;
import static seedu.address.model.internship.Status.NEW;
import static seedu.address.model.internship.Status.OFFERED;
import static seedu.address.model.internship.Status.REJECTED;

import java.util.Comparator;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import seedu.address.MainApp;
import seedu.address.model.internship.Internship;


/**
 * A UI component that displays information of a {@code Internship}.
 */
public class InternshipDetailsCard extends UiPart<Region> {
    public static final String ROLE_LABEL = "Role: ";
    private static final String FXML = "InternshipDetailsCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private VBox cardPane;
    @FXML
    private Label companyName;

    @FXML
    private Label role;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox tipBox;
    @FXML
    private Text tips;
//    @FXML
//    private ImageView tipsImage;



    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public InternshipDetailsCard(Internship internship, Scene scene) {
        super(FXML);
        this.internship = internship;

        //Add Company Name
        companyName.setText(internship.getCompanyName().fullCompanyName);

        //Add Role
        role.setText(ROLE_LABEL + internship.getRole().fullRole);

        //Add Date
        String dateLabel = getDateLabel();
        date.setText(dateLabel + internship.getDate().fullDate);

        //Add Tags
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        //Set up status label
        String statusString = internship.getStatus().toString();
        HashMap<String, Color> colorMap = setupColours();
        Color statusColor = colorMap.get(statusString);
        statusLabel.setText(statusString.toUpperCase());
        statusLabel.setBackground(new Background(new BackgroundFill(
                statusColor, new CornerRadii(10), new Insets(-5))));

        //Set up tips
        tips.setText(getTips());

        //@@author eugentangkj-reused
        //Reused with modifications from https://stackoverflow.com/questions/29315469/javafx-resize-text-with-window
        tips.wrappingWidthProperty().bind(scene.widthProperty().multiply(0.4));

        //Set up image
//        tipsImage.setImage(new Image(MainApp.class.getResourceAsStream(getTipsImage())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipDetailsCard)) {
            return false;
        }

        // state check with comparison of company name, role and date
        InternshipDetailsCard details = (InternshipDetailsCard) other;
        return companyName.getText().equals(details.companyName.getText())
                && role.getText().equals(details.role.getText())
                && date.getText().equals(details.date.getText())
                && internship.equals(details.internship);
    }


    /**
     * Initialises the colours associated with the status label.
     *
     * @return a hashmap containing the colors associated with each status type
     */
    public HashMap<String, Color> setupColours() {
        //Hashmap that stores the colours associated with each status
        HashMap<String, Color> colorMap = new HashMap<String, Color>();
        colorMap.put(NEW, Color.rgb(250, 155, 68, 1.0));
        colorMap.put(APPLIED, Color.rgb(68, 170, 250, 1.0));
        colorMap.put(ASSESSMENT, Color.rgb(250, 68, 155, 1.0));
        colorMap.put(INTERVIEW, Color.rgb(126, 68, 250, 1.0));
        colorMap.put(OFFERED, Color.rgb(42, 174, 79, 1.0));
        colorMap.put(REJECTED, Color.rgb(250, 68, 68, 1.0));
        return colorMap;
    }


    /**
     * Returns the label for the date field in Internship Details
     *
     * @return the corresponding String as a label for the date.
     */
    public String getDateLabel() {
        String dateLabel;
        switch (this.internship.getStatus().toString()) {
        case APPLIED:
            dateLabel = "Date Applied: ";
            break;
        case ASSESSMENT:
            dateLabel = "Date of Assessment: ";
            break;
        case INTERVIEW:
            dateLabel = "Date of Interview: ";
            break;
        case OFFERED:
            dateLabel = "Date of Notice of Offer: ";
            break;
        case REJECTED:
            dateLabel = "Date of Notice of Rejection: ";
            break;
        default:
            dateLabel = "Date Added: ";
        }
        return dateLabel;
    }

    /**
     * Gets the corresponding tips according to the status
     *
     * @return the tips for a specific status
     */
    public String getTips() {
        switch (this.internship.getStatus().toString()) {
        case APPLIED:
            return "longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooohiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooohiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring longstring hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooohiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
        case ASSESSMENT:
            return "Assessment tips";
        case INTERVIEW:
            return "Interview tips";
        case OFFERED:
            return "Offered tips";
        case REJECTED:
            return "Rejected tips";
        default:
            return "New tips";
        }
    }

    /**
     * Gets the corresponding image url according to the status
     *
     * @return the tips for a specific status
     */
    public String getTipsImage() {
        switch (this.internship.getStatus().toString()) {
        case APPLIED:
            return "/tips/applied.png";
        case ASSESSMENT:
            return "/tips/assessment.png";
        case INTERVIEW:
            return "/tips/interview.png";
        case OFFERED:
            return "/tips/offered.png";
        case REJECTED:
            return "/tips/rejected.png";
        default:
            return "/tips/new.png";
        }
    }
}
