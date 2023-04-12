package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonLivesInRegionPredicate;
import seedu.address.model.person.Region.Regions;

public class ListRegionCommandTest {

    private class Bird {
    }

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ListRegionCommand listNorth = new ListRegionCommand(Regions.NORTH);
        ListRegionCommand listSouth = new ListRegionCommand(Regions.SOUTH);
        ListRegionCommand listEast = new ListRegionCommand(Regions.EAST);
        ListRegionCommand listNorthOther = new ListRegionCommand(Regions.NORTH);

        assertTrue(listNorth.equals(listNorth));
        assertTrue(listSouth.equals(listSouth));
        assertTrue(listNorth.equals(listNorthOther));

        assertFalse(listNorth.equals(listEast));
        assertFalse(listEast.equals(listNorthOther));

        assertFalse(listEast.equals(null));
        assertFalse(listNorth.equals(new Bird()));
    }

    @Test
    public void execute_validRegion_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ListRegionCommand listSouth = new ListRegionCommand(Regions.SOUTH);
        PersonLivesInRegionPredicate p = new PersonLivesInRegionPredicate(Regions.SOUTH);
        expectedModel.updateFilteredPersonList(p);
        assertCommandSuccess(listSouth, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_unknownRegion_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        ListRegionCommand listUnknown = new ListRegionCommand(Regions.UNKNOWN);
        PersonLivesInRegionPredicate p = new PersonLivesInRegionPredicate(Regions.UNKNOWN);
        expectedModel.updateFilteredPersonList(p);
        assertCommandSuccess(listUnknown, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_validRegion_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ListRegionCommand listWest = new ListRegionCommand(Regions.WEST);
        PersonLivesInRegionPredicate p = new PersonLivesInRegionPredicate(Regions.WEST);
        expectedModel.updateFilteredPersonList(p);
        assertCommandSuccess(listWest, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
}
