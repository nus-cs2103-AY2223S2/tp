package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.TypicalAddressBooks;
import seedu.address.testutil.TypicalMeetings;

public class ImportMeetingsCommandTest {
    @Test
    public void success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportMeetingsCommand command = new ImportMeetingsCommand(TypicalMeetings.getTypicalMeetings(), false);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        List<Meeting> peopleToImport = List.of(
                TypicalMeetings.getTypicalMeetings().get(0),
                TypicalMeetings.getTypicalMeetings().get(1));
        model.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        ImportMeetingsCommand command = new ImportMeetingsCommand(peopleToImport, true);

        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(),
                new UserPrefs());
        expectedModel.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        expectedModel.addMeeting(TypicalMeetings.getTypicalMeetings().get(1));

        assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookMeetingsOnly(), new UserPrefs());
        ImportMeetingsCommand command = new ImportMeetingsCommand(List.of(TypicalMeetings.getTypicalMeetings().get(0)),
                false);
        assertCommandFailure(command, model, ImportMeetingsCommand.DUPLICATE_MEETING);
    }
}
