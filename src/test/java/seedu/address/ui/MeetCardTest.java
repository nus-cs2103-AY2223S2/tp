package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysRecommendation;

import org.junit.jupiter.api.Test;

import guitests.guihandles.MeetCardHandle;
import seedu.address.model.location.Location;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.recommendation.RecommendationBuilder;

public class MeetCardTest extends GuiUnitTest {
    @Test
    public void display() {
        Recommendation recommendation = new RecommendationBuilder().build();
        MeetCard meetCard = new MeetCard(recommendation);
        uiPartExtension.setUiPart(meetCard);
        assertCardDisplay(meetCard, recommendation);
    }

    @Test
    public void equals() {
        Recommendation recommendation = new RecommendationBuilder().build();
        MeetCard meetCard = new MeetCard(recommendation);

        // same recommendation -> returns true
        MeetCard copy = new MeetCard(recommendation);
        assertTrue(meetCard.equals(copy));

        // same object -> returns true
        assertTrue(meetCard.equals(meetCard));

        // null -> returns false
        assertFalse(meetCard.equals(null));

        // different types -> returns false
        assertFalse(meetCard.equals(0));

        // different recommendation -> returns false
        Recommendation differentRecommendation = new RecommendationBuilder().withLocation(Location.NUS).build();
        assertFalse(meetCard.equals(new MeetCard(differentRecommendation)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedPerson} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(MeetCard meetCard, Recommendation expectedRecommendation) {
        guiRobot.pauseForHuman();

        MeetCardHandle meetCardHandle = new MeetCardHandle(meetCard.getRoot());

        // verify person details are displayed correctly
        assertCardDisplaysRecommendation(expectedRecommendation, meetCardHandle);
    }
}
