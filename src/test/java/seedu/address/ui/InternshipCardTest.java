package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.internship.Status.APPLIED;
import static seedu.address.model.internship.Status.ASSESSMENT;
import static seedu.address.model.internship.Status.INTERVIEW;
import static seedu.address.model.internship.Status.NEW;
import static seedu.address.model.internship.Status.OFFERED;
import static seedu.address.model.internship.Status.REJECTED;
import static seedu.address.ui.InternshipCard.ROLE_LABEL;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternships;

public class InternshipCardTest extends GuiUnitTest {
    @Test
    public void create_internshipCard_success() {
        //@@author eugenetangkj-reused
        //Reused with modifications from the following link for testing of creation of internship card in GUI testing
        // https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/InternshipCardTest.java
        Internship internship = new InternshipBuilder().build();
        InternshipCard internshipCard = new InternshipCard(internship, 1);
        new UiPartExtension().setUiPart(internshipCard);
    }

    @Test
    public void display_internshipCard_success() {
        Internship internship = new InternshipBuilder().build();
        InternshipCard internshipCard = new InternshipCard(internship, 1);
        uiPartExtension.setUiPart(internshipCard);
        assertInternshipCardEqual(internshipCard, internship, 1);
    }

    @Test
    public void equals() {
        InternshipCard internshipCardAmazonOne = new InternshipCard(TypicalInternships.AMAZON, 1);
        InternshipCard internshipCardAmazonTwo = new InternshipCard(TypicalInternships.AMAZON, 2);
        InternshipCard internshipCardAmazonOneDuplicate = new InternshipCard(TypicalInternships.AMAZON, 1);
        //Same object
        assertTrue(internshipCardAmazonOne.equals(internshipCardAmazonOne));

        //Not an instance of internship card
        assertFalse(internshipCardAmazonOne.equals(TypicalInternships.AMAZON));

        //Same internship but different index should not be equal
        assertFalse(internshipCardAmazonOne.equals(internshipCardAmazonTwo));

        //Different objects but same internship and index
        assertTrue(internshipCardAmazonOne.equals(internshipCardAmazonOneDuplicate));
    }

    @Test
    public void create_colorMap_success() {
        HashMap<String, Color> colorMap = new InternshipCard(TypicalInternships.AMAZON, 1).setupColours();
        //Check "New" color
        assertTrue(colorMap.get(NEW).equals(Color.rgb(250, 155, 68, 1.0)));
        //Check "Applied" color
        assertTrue(colorMap.get(APPLIED).equals(Color.rgb(68, 170, 250, 1.0)));
        //Check "Assessment" color
        assertTrue(colorMap.get(ASSESSMENT).equals(Color.rgb(250, 68, 155, 1.0)));
        //Check "Interview" color
        assertTrue(colorMap.get(INTERVIEW).equals(Color.rgb(126, 68, 250, 1.0)));
        //Check "Offered" color
        assertTrue(colorMap.get(OFFERED).equals(Color.rgb(42, 174, 79, 1.0)));
        //Check "Rejected" color
        assertTrue(colorMap.get(REJECTED).equals(Color.rgb(250, 68, 68, 1.0)));
    }

    @ Test
    public void create_dateLabel_success() {
        // Test new
        Internship internship = new InternshipBuilder().withStatus(NEW).build();
        InternshipCard internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date Added: ");
        // Test applied
        internship = new InternshipBuilder().withStatus(APPLIED).build();
        internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date Applied: ");
        // Test Assessment
        internship = new InternshipBuilder().withStatus(ASSESSMENT).build();
        internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date of Assessment: ");
        // Test Interview
        internship = new InternshipBuilder().withStatus(INTERVIEW).build();
        internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date of Interview: ");
        // Test Offered
        internship = new InternshipBuilder().withStatus(OFFERED).build();
        internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date of Notice of Offer: ");
        // Test rejected
        internship = new InternshipBuilder().withStatus(REJECTED).build();
        internshipCard = new InternshipCard(internship, 1);
        assertEquals(internshipCard.getDateLabel(), "Date of Notice of Rejection: ");
    }


    private void assertInternshipCardEqual(InternshipCard internshipCard, Internship internship, int expectedIndex) {
        //@@author potty10-reused
        //Reused with some modification from
        //https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/InternshipCardTest.java
        Region internshipRegion = internshipCard.getRoot();
        Label companyName = (Label) internshipRegion.lookup("#companyName");
        Label internshipRoleLabel = (Label) internshipRegion.lookup("#role");
        Label internshipDate = (Label) internshipRegion.lookup("#date");
        Label internshipStatus = (Label) internshipRegion.lookup("#statusLabel");
        String expectedDateLabel = internshipCard.getDateLabel();
        ObservableList<Node> internshipNodeTags = ((FlowPane) internshipRegion.lookup("#tags")).getChildren();

        assertEquals(companyName.getText(), internship.getCompanyName().toString());
        assertEquals(internshipRoleLabel.getText(), ROLE_LABEL + internship.getRole().toString());
        assertEquals(internshipDate.getText(), expectedDateLabel + internship.getDate().toString());
        assertEquals(internshipStatus.getText(), internship.getStatus().toString().toUpperCase());
        assertIterableEquals(internshipNodeTags.stream().map(node -> ((Label) node).getText()).sorted()
                .collect(Collectors.toList()), internship.getTags().stream().sorted().collect(Collectors.toList()));
        //@@author
    }

}
