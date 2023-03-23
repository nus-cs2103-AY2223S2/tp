package seedu.address.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.storage.JsonAdaptedMeeting;

/**
 * This class represents a command for exporting of meetings
 */
public class ExportMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "exportm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports meetings in JSON format\n" + "Parameters: m/ "
            + "index of meeting";
    public static final String INDEX_NOT_FOUND = "One of the provided indices is not found";

    private final List<Index> indexList;

    public ExportMeetingsCommand(List<Index> indexList) {
        this.indexList = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            List<Meeting> personList = model.getMeetingsByIndexes(indexList);
            List<JsonAdaptedMeeting> adapted =
                    personList
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
}
