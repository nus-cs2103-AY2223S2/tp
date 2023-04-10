package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

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

public class MarkAsDoneCommandTest {
    private final Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());

    //EP: indexes within meeting list size
    @Test
    public void index_inRange_success() {
        List<Index> indexList = List.of(new Index[]{
            INDEX_FIRST_MEETING, TypicalIndexes.INDEX_SECOND_MEETING});
        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(indexList);
        String expectedMessage = String.format(MarkAsDoneCommand.SUCCESS_FORMAT,
            indexList.stream().map(Index::getOneBased).collect(Collectors.toList()));
        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());
        model.markMeetingsAsDone(indexList);

        CommandTestUtil.assertCommandSuccess(markAsDoneCommand, model, expectedMessage, model);
    }

    //EP: index > size of meeting list
    @Test
    public void index_outOfRange_failure() {
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(meetings.size())});
        MarkAsDoneCommand markAsDoneCommand = new MarkAsDoneCommand(indexList);
        String expectedMessage = MarkAsDoneCommand.INDEX_NOT_FOUND;
        CommandTestUtil.assertCommandFailure(markAsDoneCommand, model, expectedMessage);
    }
}
