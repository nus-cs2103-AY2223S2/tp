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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.storage.JsonAdaptedMeeting;
import seedu.address.testutil.MeetingBuilder;

public class ExportMeetingsCommandTest {
    @Test
    public void index_inRange_success() {
        Model model = new ModelManager(getTypicalAddressBookMeetingsOnly(), new UserPrefs());

        List<Index> indexList = List.of(new Index[]{INDEX_FIRST_MEETING, INDEX_SECOND_MEETING});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList, null, null);
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
        Model model = new ModelManager(getTypicalAddressBookMeetingsOnly(), new UserPrefs());

        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        List<Index> indexList = List.of(new Index[]{Index.fromZeroBased(meetings.size())});
        ExportMeetingsCommand exportMeetingsCommand = new ExportMeetingsCommand(indexList, null, null);

        String expectedMessage = ExportMeetingsCommand.INDEX_NOT_FOUND;

        assertCommandFailure(exportMeetingsCommand, model, expectedMessage);
    }

    @Test
    public void date_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
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

        assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }

    @Test
    public void dateWithIndex_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
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

        assertCommandSuccess(exportMeetingsCommand, model, expectedMessage, model);
    }
}
