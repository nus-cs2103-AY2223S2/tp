package seedu.quickcontacts.logic.commands;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.storage.JsonAdaptedMeeting;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.TypicalIndexes;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class ExportMeetingsCommandTest {
    @Test
    public void index_inRange_success() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());

        List<Index> indexList = List.of(new Index[]{
            TypicalIndexes.INDEX_FIRST_MEETING, TypicalIndexes.INDEX_SECOND_MEETING});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList, null, null);
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{
                new JsonAdaptedMeeting(meetings.get(TypicalIndexes.INDEX_FIRST_MEETING.getZeroBased())),
                new JsonAdaptedMeeting(meetings.get(TypicalIndexes.INDEX_SECOND_MEETING.getZeroBased()))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CommandTestUtil.assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }

    @Test
    public void index_outOfRange_failure() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookMeetingsOnly(), new UserPrefs());

        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(meetings.size())});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList, null, null);

        String expectedMessage = ExportMeetingsCommand.INDEX_NOT_FOUND;

        CommandTestUtil.assertCommandFailure(exportMeetingsCommand, model, expectedMessage);
    }

    @Test
    public void date_success() {
        Model model = new ModelManager(new QuickBook(), new UserPrefs());
        model.addMeeting(new MeetingBuilder().withDateTime("01/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("02/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("03/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("04/01/1970").build());
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{});
        ExportMeetingsCommand exportMeetingsCommand =
                new ExportMeetingsCommand(indexList, new DateTime("01/01/1970"), new DateTime("03/01/1970"));

        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{
                new JsonAdaptedMeeting(meetings.get(0)),
                new JsonAdaptedMeeting(meetings.get(1)),
                new JsonAdaptedMeeting(meetings.get(2))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CommandTestUtil.assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }

    @Test
    public void dateWithIndex_success() {
        Model model = new ModelManager(new QuickBook(), new UserPrefs());
        model.addMeeting(new MeetingBuilder().withDateTime("01/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("02/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("03/01/1970").build());
        model.addMeeting(new MeetingBuilder().withDateTime("04/01/1970").build());
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(0)});

        ExportMeetingsCommand exportMeetingsCommand =
                new ExportMeetingsCommand(indexList, new DateTime("02/01/1970"), new DateTime("04/01/1970"));

        String expectedMessage;
        try {
            expectedMessage = JsonUtil.toJsonString(new JsonAdaptedMeeting[]{
                new JsonAdaptedMeeting(meetings.get(0)),
                new JsonAdaptedMeeting(meetings.get(1)),
                new JsonAdaptedMeeting(meetings.get(2)),
                new JsonAdaptedMeeting(meetings.get(3))
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CommandTestUtil.assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }
}
