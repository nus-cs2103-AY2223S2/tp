package seedu.address.ui;

import static seedu.address.model.internship.Status.ACCEPTED;
import static seedu.address.model.internship.Status.APPLIED;
import static seedu.address.model.internship.Status.ASSESSMENT;
import static seedu.address.model.internship.Status.INTERVIEW;
import static seedu.address.model.internship.Status.OFFERED;
import static seedu.address.model.internship.Status.REJECTED;

import java.util.Comparator;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Text comment;
    @FXML
    private FlowPane tags;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox tipBox;
    @FXML
    private Text tips;

    /**
     * A UI component that displays the detailed information and tips of a {@code Internship}.
     */
    public InternshipDetailsCard(Internship internship, Scene scene) {
        super(FXML);
        this.internship = internship;

        //Add Company Name
        companyName.setText(internship.getCompanyName().fullCompanyName);

        //Add Role
        role.setText(ROLE_LABEL + internship.getRole().fullRole);

        //Add Date
        String dateLabel = InternshipCard.getDateLabel(internship.getStatus().toString());
        date.setText(dateLabel + internship.getDate().fullDate);

        //Add Comment
        comment.setText(internship.getComment().commentContent);
        //@@author eugenetangkj-reused
        //Reused with modifications from https://stackoverflow.com/questions/29315469/javafx-resize-text-with-window
        comment.wrappingWidthProperty().bind(scene.widthProperty().multiply(0.4));

        //Add Tags
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        //Set up status label
        String statusString = internship.getStatus().toString();
        HashMap<String, Color> colorMap = InternshipCard.setupColours();
        Color statusColor = colorMap.get(statusString);
        statusLabel.setText(statusString.toUpperCase());
        statusLabel.setBackground(new Background(new BackgroundFill(
                statusColor, new CornerRadii(10), new Insets(-5))));

        //Set up tips
        tips.setText(getTips());

        //@@author eugenetangkj-reused
        //Reused with modifications from https://stackoverflow.com/questions/29315469/javafx-resize-text-with-window
        tips.wrappingWidthProperty().bind(scene.widthProperty().multiply(0.4));

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
        return internship.equals(details.internship);
    }


    /**
     * Gets the corresponding tips according to the status
     *
     * @return the tips for a specific status
     */
    public String getTips() {
        switch (this.internship.getStatus().toString()) {
        case APPLIED:
            return "While waiting for the company's response, you can try applying to other companies as well"
                    + " to have a higher chance of landing an internship.";
        case ASSESSMENT:
            return "Practice makes perfect! Visit sites such as HackerRank and LeetCode to practice your algorithms"
                    + " and problem-solving skills. You could also attempt the practices under a time trial to give"
                    + " you a better sense of the actual coding assignment.";
        case INTERVIEW:
            return "Be natural! The role of the interviewer is not to put you in a tight position, but rather to"
                    + " learn more about who you are as a person. It's good if you could share what makes you special"
                    + " and about your personalised experience that makes you suitable for the job.";
        case OFFERED:
            return "Congratulations! Your hard work has paid off. Remember to read through the details of the"
                    + " letter of offer such as job scope and working hours before committing to the offer.";
        case REJECTED:
            return "Fret not! The process of landing an internship is not a smooth-sailing one, and failures are"
                    + " part of the journey. Continue your search and you will eventually a suitable internship."
                    + " Fighting!";
        case ACCEPTED:
            return "Congratulations! This is a chance to build new skills, make connections, and explore your "
                    + "interests in a real-world setting. Embrace every moment of this journey and "
                    + "don't be afraid to ask questions, seek guidance, and take risks.";
        default:
            return "If possible, try to apply early because once companies receive applications, they would start"
                    + " screening for potential candidates. Also, remember to do a thorough check of your resume"
                    + " before sending out your application.";
        }
    }
}
