package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBookMeetingsOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.storage.JsonAdaptedMeeting;

public class ExportMeetingsCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBookMeetingsOnly(), new UserPrefs());

    @Test
    public void index_inRange_success() {
        List<Index> indexList = List.of(new Index[]{INDEX_FIRST_MEETING, INDEX_SECOND_MEETING});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList);
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{
                new JsonAdaptedMeeting(meetings.get(INDEX_FIRST_MEETING.getZeroBased())),
                new JsonAdaptedMeeting(meetings.get(INDEX_SECOND_MEETING.getZeroBased()))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }

    @Test
    public void index_outOfRange_failure() {
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(meetings.size())});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList);

        String expectedMessage = ExportMeetingsCommand.INDEX_NOT_FOUND;


        assertCommandFailure(exportMeetingsCommand, model, expectedMessage);
    }
}
