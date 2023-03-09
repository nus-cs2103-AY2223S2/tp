package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalInternships.APPLE;
import static seedu.address.testutil.TypicalInternships.FOODPANDA;
import static seedu.address.testutil.TypicalInternships.GOLDMAN;
import static seedu.address.testutil.TypicalInternships.GOOGLE;
import static seedu.address.testutil.TypicalInternships.GRAB;
import static seedu.address.testutil.TypicalInternships.SAMSUNG;
import static seedu.address.testutil.TypicalInternships.SUPERCELLGAMES;

import org.junit.jupiter.api.Test;

import seedu.address.Main;

public class InternshipCardTest {

    @Test
    public void isValidSetDateLabel() {
        //Different internship cards
        Main.main(null);
        assertFalse(new InternshipCard(APPLE, 1).equals(new InternshipCard(APPLE, 2)));
        assertFalse(new InternshipCard(APPLE, 1).equals(new InternshipCard(GOOGLE, 1)));

        //Same internship card
        assertTrue(new InternshipCard(APPLE, 1).equals(new InternshipCard(APPLE, 1)));


    }
    @Test
    public void isValidDateLabel() {
        assertTrue(new InternshipCard(SUPERCELLGAMES, 1).getDateLabel().equals("Date Added: "));
        assertTrue(new InternshipCard(SAMSUNG, 1).getDateLabel().equals("Date Applied: "));
        assertTrue(new InternshipCard(FOODPANDA, 1).getDateLabel().equals("Date of Assessment: "));
        assertTrue(new InternshipCard(APPLE, 1).getDateLabel().equals("Date of Interview: "));
        assertTrue(new InternshipCard(GOLDMAN, 1).getDateLabel().equals("Date of Notice of Offer: "));
        assertTrue(new InternshipCard(GRAB, 1).getDateLabel().equals("Date of Notice of Rejection: "));
    }
}
