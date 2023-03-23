package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.internship.Status.ACCEPTED;
import static seedu.address.model.internship.Status.APPLIED;
import static seedu.address.model.internship.Status.ASSESSMENT;
import static seedu.address.model.internship.Status.INTERVIEW;
import static seedu.address.model.internship.Status.NEW;
import static seedu.address.model.internship.Status.OFFERED;
import static seedu.address.model.internship.Status.REJECTED;
import static seedu.address.ui.InternshipCard.ROLE_LABEL;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternships;

public class InternshipDetailsCardTest extends GuiUnitTest {
    @Test
    public void create_internshipCard_success() {
        //@@author eugenetangkj-reused
        //Reused with modifications from the following link for testing of creation of internship card in GUI testing
        // https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/InternshipCardTest.java
        Internship internship = new InternshipBuilder().build();
        // @@author eugenetangkj-reused
        // Sample scene reused from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        Group rootGroup = new Group();
        Scene sceneNew = new Scene(rootGroup, 300, 300, Color.BLACK);
        InternshipDetailsCard internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        new UiPartExtension().setUiPart(internshipDetailsCard);
    }

    //@@author eugenetangkj-reused
    //Reused from teammate potty10's code in InternshipCard.java
    @Test
    public void display_internshipDetailsCard_success() {
        Internship internship = new InternshipBuilder().build();

        // @@author eugenetangkj-reused
        // Sample scene reused from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        Group rootGroup = new Group();
        Scene sceneNew = new Scene(rootGroup, 300, 300, Color.BLACK);

        InternshipDetailsCard internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        uiPartExtension.setUiPart(internshipDetailsCard);
        assertInternshipDetailsCardEqual(internshipDetailsCard, internship);
    }

    @Test
    public void equals() {
        // @@author eugenetangkj-reused
        // Sample scene reused from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        Group rootGroup = new Group();
        Scene sceneNew = new Scene(rootGroup, 300, 300, Color.BLACK);

        InternshipDetailsCard internshipDetailsCardAmazonOne =
                new InternshipDetailsCard(TypicalInternships.AMAZON, sceneNew);

        InternshipDetailsCard internshipDetailsCardAmazonOneDuplicate =
                new InternshipDetailsCard(TypicalInternships.AMAZON, sceneNew);

        InternshipDetailsCard internshipDetailsCardTesla =
                new InternshipDetailsCard(TypicalInternships.TESLA, sceneNew);

        // Same object
        assertTrue(internshipDetailsCardAmazonOne.equals(internshipDetailsCardAmazonOne));

        // Not an instance of internship card
        assertFalse(internshipDetailsCardAmazonOne.equals(TypicalInternships.AMAZON));

        // Different objects but same internship
        assertTrue(internshipDetailsCardAmazonOne.equals(internshipDetailsCardAmazonOneDuplicate));

        // Different internships
        assertFalse(internshipDetailsCardAmazonOne.equals(internshipDetailsCardTesla));
    }


    @ Test
    public void getTips_success() {
        // @@author eugenetangkj-reused
        // Sample scene reused from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html
        Group rootGroup = new Group();
        Scene sceneNew = new Scene(rootGroup, 300, 300, Color.BLACK);

        // Test New
        Internship internship = new InternshipBuilder().withStatus(NEW).build();
        InternshipDetailsCard internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "If possible, try to apply early because once companies receive applications, they would start"
                        + " screening for potential candidates. Also, remember to do a thorough check of your resume"
                        + " before sending out your application.");

        // Test Applied
        internship = new InternshipBuilder().withStatus(APPLIED).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "While waiting for the company's response, you can try applying to other companies as well"
                        + " to have a higher chance of landing an internship.");

        // Test Assessment
        internship = new InternshipBuilder().withStatus(ASSESSMENT).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "Practice makes perfect! Visit sites such as HackerRank and LeetCode to practice your"
                        + " algorithms and problem-solving skills. You could also attempt the practices under a time"
                        + " trial to give you a better sense of the actual coding assignment.");

        // Test Interview
        internship = new InternshipBuilder().withStatus(INTERVIEW).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "Be natural! The role of the interviewer is not to put you in a tight position, but rather to"
                        + " learn more about who you are as a person. It's good if you could share what makes you"
                        + " special and about your personalised experience that makes you suitable for the job.");

        // Test Offered
        internship = new InternshipBuilder().withStatus(OFFERED).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "Congratulations! Your hard work has paid off. Remember to read through the details of the"
                        + " letter of offer such as job scope and working hours before committing to the offer.");

        // Test Rejected
        internship = new InternshipBuilder().withStatus(REJECTED).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "Fret not! The process of landing an internship is not a smooth-sailing one, and failures are"
                        + " part of the journey. Continue your search and you will eventually a suitable internship."
                        + " Fighting!");

        // Test Accepted
        internship = new InternshipBuilder().withStatus(ACCEPTED).build();
        internshipDetailsCard = new InternshipDetailsCard(internship, sceneNew);
        assertEquals(internshipDetailsCard.getTips(),
                "Congratulations! This is a chance to build new skills, make connections, and explore your "
                        + "interests in a real-world setting. Embrace every moment of this journey and "
                        + "don't be afraid to ask questions, seek guidance, and take risks.");

    }

    private void assertInternshipDetailsCardEqual(InternshipDetailsCard internshipDetailCard, Internship internship) {
        //@@author eugenetangkj-reused
        //Reused from teammate potty10 where he modified the code from
        //https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/InternshipCardTest.java
        Region internshipRegion = internshipDetailCard.getRoot();
        Label companyName = (Label) internshipRegion.lookup("#companyName");
        Label internshipRoleLabel = (Label) internshipRegion.lookup("#role");
        Label internshipDate = (Label) internshipRegion.lookup("#date");
        Label internshipStatus = (Label) internshipRegion.lookup("#statusLabel");
        String expectedDateLabel = InternshipCard.getDateLabel(internship.getStatus().toString());
        Text comment = (Text) internshipRegion.lookup("#comment");
        ObservableList<Node> internshipNodeTags = ((FlowPane) internshipRegion.lookup("#tags")).getChildren();
        assertEquals(companyName.getText(), internship.getCompanyName().toString());
        assertEquals(internshipRoleLabel.getText(), ROLE_LABEL + internship.getRole().toString());

        assertEquals(internshipDate.getText(), expectedDateLabel + internship.getDate().toString());
        assertEquals("[" + comment.getText() + "]", internship.getComment().toString());
        assertEquals(internshipStatus.getText(), internship.getStatus().toString().toUpperCase());
        assertIterableEquals(internshipNodeTags.stream().map(node -> ((Label) node).getText()).sorted()
                .collect(Collectors.toList()), internship.getTags().stream().sorted().collect(Collectors.toList()));

    }

}

