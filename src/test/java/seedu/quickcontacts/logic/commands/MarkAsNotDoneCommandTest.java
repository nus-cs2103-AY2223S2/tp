package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.quickcontacts.testutil.TypicalMeetings.DONE_MEETING;
import static seedu.quickcontacts.testutil.TypicalMeetings.DONE_MEETING2;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.testutil.TypicalIndexes;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class MarkAsNotDoneCommandTest {
    private final Model model = new ModelManager();

    //EP: indexes within meeting list size
    @Test
    public void index_inRange_success() {
        List<Index> indexList = List.of(new Index[]{
            INDEX_FIRST_MEETING, TypicalIndexes.INDEX_SECOND_MEETING});
        model.addMeeting(DONE_MEETING);
        model.addMeeting(DONE_MEETING2);
        MarkAsNotDoneCommand markAsNotDoneCommand = new MarkAsNotDoneCommand(indexList);
        String expectedMessage = String.format(MarkAsNotDoneCommand.SUCCESS_FORMAT,
                indexList.stream().map(Index::getOneBased).collect(Collectors.toList()));
        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());
        expectedModel.markMeetingsAsNotDone(indexList);

        CommandTestUtil.assertCommandSuccess(markAsNotDoneCommand, model, expectedMessage, model);
    }

    //EP: index > size of meeting list
    @Test
    public void index_outOfRange_failure() {
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(meetings.size())});
        MarkAsNotDoneCommand markAsNotDoneCommand = new MarkAsNotDoneCommand(indexList);
        String expectedMessage = MarkAsNotDoneCommand.INDEX_NOT_FOUND;
        CommandTestUtil.assertCommandFailure(markAsNotDoneCommand, model, expectedMessage);
    }
}
