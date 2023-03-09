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
}
