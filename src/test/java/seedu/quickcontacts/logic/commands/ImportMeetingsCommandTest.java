package seedu.quickcontacts.logic.commands;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.TypicalMeetings;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class ImportMeetingsCommandTest {
    @Test
    public void success() {
        Model model = new ModelManager(new QuickBook(), new UserPrefs());
        ImportMeetingsCommand command = new ImportMeetingsCommand(TypicalMeetings.getTypicalMeetings(), false);
        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(),
                new UserPrefs());
        CommandTestUtil.assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(),
                new UserPrefs());
        List<Meeting> peopleToImport = List.of(
                TypicalMeetings.getTypicalMeetings().get(0),
                TypicalMeetings.getTypicalMeetings().get(1));
        model.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        ImportMeetingsCommand command = new ImportMeetingsCommand(peopleToImport, true);

        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(),
                new UserPrefs());
        expectedModel.removeMeeting(TypicalMeetings.getTypicalMeetings().get(1));
        expectedModel.addMeeting(TypicalMeetings.getTypicalMeetings().get(1));

        CommandTestUtil.assertCommandSuccess(command, model, ImportMeetingsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());
        ImportMeetingsCommand command = new ImportMeetingsCommand(List.of(TypicalMeetings.getTypicalMeetings().get(0)),
                false);
        CommandTestUtil.assertCommandFailure(command, model, ImportMeetingsCommand.DUPLICATE_MEETING);
    }
}
