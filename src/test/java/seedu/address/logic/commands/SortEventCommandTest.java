package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.BIRTHDAY_PARTY;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalEvents.SPORTS_DAY;
import static seedu.address.testutil.TypicalEvents.WEDDING_DINNER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.SortEventType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortEventCommand}.
 */
public class SortEventCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SortEventType sortByStartDateTime = SortEventType.SORT_BY_START_DATE_TIME;
        SortEventType sortByNameInAsc = SortEventType.SORT_BY_NAME_ASC;
        SortEventCommand sortEventFirstCommand = new SortEventCommand(sortByStartDateTime);
        SortEventCommand sortEventSecondCommand = new SortEventCommand(sortByNameInAsc);

        // same object -> returns true
        assertTrue(sortEventFirstCommand.equals(sortEventFirstCommand));

        // same values -> returns true
        SortEventCommand sortEventFirstCommandCopy = new SortEventCommand(sortByStartDateTime);
        assertTrue(sortEventFirstCommand.equals(sortEventFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortEventFirstCommand.equals(null));

        // different sort type -> returns false
        assertFalse(sortEventFirstCommand.equals(sortEventSecondCommand));
    }

    @Test
    public void execute_sortByNameInAscOrder_sortSuccessful() {
        SortEventType sortType = SortEventType.SORT_BY_NAME_ASC;
        SortEventCommand command = new SortEventCommand(sortType);
        String expectedMessage = String.format("%s based on the %s", SortEventCommand.MESSAGE_SUCCESS,
                sortType.getDescription());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortEventList(sortType);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BIRTHDAY_PARTY, CARNIVAL, SPORTS_DAY, WEDDING_DINNER), model.getFilteredEventList());
    }
}
