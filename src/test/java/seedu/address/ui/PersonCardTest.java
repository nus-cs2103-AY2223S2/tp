package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.jupiter.api.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

// @@author seanfirefox-reused
/**
 * GUI test for PersonCard. Referenced from AB4.
 */
public class PersonCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Person personWithNoTags = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(personWithNoTags);
        uiPartExtension.setUiPart(personCard);
        assertCardDisplay(personCard, personWithNoTags);

        // with tags
        Person personWithTags = new PersonBuilder()
                .withGroupTags("NS", "CCA")
                .build();
        personCard = new PersonCard(personWithTags);
        uiPartExtension.setUiPart(personCard);
        assertCardDisplay(personCard, personWithTags);
    }

    @Test
    public void equals() {
        Person person = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(person);

        // same person-> returns true
        PersonCard copy = new PersonCard(person);
        assertTrue(personCard.equals(copy));

        // same object -> returns true
        assertTrue(personCard.equals(personCard));

        // null -> returns false
        assertFalse(personCard.equals(null));

        // different types -> returns false
        assertFalse(personCard.equals(0));

        // different person, same index -> returns false
        Person differentPerson = new PersonBuilder().withName("differentName").build();
        assertFalse(personCard.equals(new PersonCard(differentPerson)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedPerson} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(PersonCard personCard, Person expectedPerson) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(personCard.getRoot());

        // verify person details are displayed correctly
        assertCardDisplaysPerson(expectedPerson, personCardHandle);
    }
}
