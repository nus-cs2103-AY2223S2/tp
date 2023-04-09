package seedu.quickcontacts.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.storage.JsonAdaptedMeeting;

/**
 * This class represents a command for exporting of meetings
 */
public class ExportMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "exportm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports meetings in JSON format\n" + "Parameters: m/ "
            + "index of meeting start/: optional starting point of range end/: optional ending point of range";
    public static final String INDEX_NOT_FOUND = "One of the provided indices is not found";
    public static final String COMMAND_DESCRIPTION = "Exports meetings in JSON format.";

    private final List<Index> indexList;

    private final DateTime start;

    private final DateTime end;

    /**
     * Creates a new export meetings command
     *
     * @param indexList list of indexes to export
     * @param start     minimum date of meetings
     * @param end       maximum date of meetings
     */
    public ExportMeetingsCommand(List<Index> indexList, DateTime start, DateTime end) {
        this.indexList = indexList;
        this.start = start;
        this.end = end;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            List<Meeting> meetingList = model.getMeetingsByIndexesAndStartEnd(indexList, this.start, this.end);
            List<JsonAdaptedMeeting> adapted =
                    meetingList
                            .stream()
                            .map(JsonAdaptedMeeting::new)
                            .collect(Collectors.toList());
            return new CommandResult(JsonUtil.toJsonString(adapted));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(INDEX_NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new CommandException("Error turning to json");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportMeetingsCommand
                && indexList.equals(((ExportMeetingsCommand) other).indexList));
    }
}
