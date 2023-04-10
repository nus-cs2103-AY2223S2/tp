package seedu.quickcontacts.logic.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.MeetingBuilder;

public class ShowNotDoneCommandTest {
    @Test
    public void success() {
        final Meeting tomorrow = new MeetingBuilder()
                .withDateTime(LocalDate.now()
                        .plusDays(1)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                .build();
        final Meeting yesterday = new MeetingBuilder()
                .withDateTime(LocalDate.now()
                        .minusDays(1)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                .build();
        ModelManager model = new ModelManager();
        ShowNotDoneCommand showNotDoneCommand = new ShowNotDoneCommand();
        model.addMeeting(tomorrow);
        model.addMeeting(yesterday);
        ModelManager expectedModel = new ModelManager();
        expectedModel.addMeeting(tomorrow);
        expectedModel.addMeeting(yesterday);
        expectedModel.updateFilteredMeetingList(Model.PREDICATE_UNDONE_MEETINGS);
        CommandTestUtil.assertCommandSuccess(showNotDoneCommand, model, ShowNotDoneCommand.MESSAGE, expectedModel);
    }
}
