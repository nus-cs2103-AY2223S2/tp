package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

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
        assertTrue(colorMap.get("New").equals(Color.rgb(250, 155, 68, 1.0)));
        //Check "Applied" color
        assertTrue(colorMap.get("Applied").equals(Color.rgb(68, 170, 250, 1.0)));
        //Check "Assessment" color
        assertTrue(colorMap.get("Assessment").equals(Color.rgb(250, 68, 155, 1.0)));
        //Check "Interview" color
        assertTrue(colorMap.get("Interview").equals(Color.rgb(126, 68, 250, 1.0)));
        //Check "Offered" color
        assertTrue(colorMap.get("Offered").equals(Color.rgb(42, 174, 79, 1.0)));
        //Check "Rejected" color
        assertTrue(colorMap.get("Rejected").equals(Color.rgb(250, 68, 68, 1.0)));
    }

    @Test
    public void set_fields_success() {
        InternshipCard internshipCardAmazonOne = new InternshipCard(TypicalInternships.AMAZON, 1);
        ArrayList<String> internshipCardInformation = internshipCardAmazonOne.getInternshipCardInformation();

        //Check Index
        assertTrue(internshipCardInformation.get(0).equals("1. "));

        //Check Company Name
        assertTrue(internshipCardInformation.get(1).equals("Amazon"));

        //Check Role
        assertTrue(internshipCardInformation.get(2).equals("Role: " + "Cloud Architect"));

        //Check Date
        assertTrue(internshipCardInformation.get(3).equals("Date of Assessment: " + "2023-02-01"));

        //Check Tags
        assertTrue(internshipCardInformation.get(4).equals("aws"));
        assertTrue(internshipCardInformation.get(5).equals("back"));

        //Check Status Label
        assertTrue(internshipCardInformation.get(6).equals("ASSESSMENT"));
    }
}
