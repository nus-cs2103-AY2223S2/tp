package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternships;


import static seedu.address.ui.InternshipCard.ROLE_LABEL;

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


    private void assertInternshipCardEqual(InternshipCard internshipCard, Internship internship, int expectedIndex) {
        //@@author potty10-reused
        //Reused with some modification from
        //https://github.com/AY2223S1-CS2103T-W17-4/tp/blob/master/src/test/java/seedu/phu/ui/InternshipCardTest.java
        Region internshipRegion = internshipCard.getRoot();
        Label companyName = (Label) internshipRegion.lookup("#companyName");
        Label internshipRoleLabel = (Label) internshipRegion.lookup("#role");
        Label internshipDate = (Label) internshipRegion.lookup("#date");
        Label internshipStatus = (Label) internshipRegion.lookup("#statusLabel");
        String expectedDateLabel = internshipCard.getDateLabel(internship.getStatus());
        ObservableList<Node> internshipNodeTags = ((FlowPane) internshipRegion.lookup("#tags")).getChildren();

        assertEquals(companyName.getText(), internship.getCompanyName().toString());
        assertEquals(internshipRoleLabel.getText(), ROLE_LABEL + internship.getRole().toString());
        assertEquals(internshipDate.getText(), expectedDateLabel + internship.getDate().toString());
        assertEquals(internshipStatus.getText(), internship.getStatus().toString().toUpperCase());
        assertIterableEquals(internshipNodeTags.stream().map(node -> ((Label) node).getText()).sorted()
                .collect(Collectors.toList()), internship.getTags().stream().sorted().collect(Collectors.toList()) );
        //@@author
    }

}
