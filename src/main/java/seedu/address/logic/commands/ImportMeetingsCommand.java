package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.storage.JsonAdaptedMeeting;

/**
 * This class represents a command for importing of meetings
 */
public class ImportMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "importm";
    static final String MALFORMED_JSON = "JSON input malformed";
    static final String DUPLICATE_MEETING = "Duplicate meeting found";
    static final String SUCCESS = "Meetings imported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports meetings in JSON format\n" + "Parameters: f/ "
            + "true: to force imports regardless of duplicates";
    private final String json;
    private final boolean isForced;

    /**
     * Creates a new ImportMeetingsCommand
     *
     * @param json     meeting to be imported
     * @param isForced whether to force imports regardless of duplicates
     */
    public ImportMeetingsCommand(String json, boolean isForced) {
        this.json = json;
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            JsonAdaptedMeeting[] meetingList = JsonUtil.fromJsonString(json, JsonAdaptedMeeting[].class);
            if (isForced) {
                for (JsonAdaptedMeeting jsonAdaptedMeeting : meetingList) {
                    if (!model.hasMeeting(jsonAdaptedMeeting.toModelType())) {
                        model.addMeeting(jsonAdaptedMeeting.toModelType());
                    }
                }
            } else {
                for (JsonAdaptedMeeting jsonAdaptedMeeting : meetingList) {
                    if (model.hasMeeting(jsonAdaptedMeeting.toModelType())) {
                        throw new DuplicateMeetingException();
                    }
                }
                for (JsonAdaptedMeeting p : meetingList) {
                    model.addMeeting(p.toModelType());
                }
            }
            return new CommandResult(SUCCESS);
        } catch (IOException | IllegalValueException e) {
            throw new CommandException(MALFORMED_JSON);
        } catch (DuplicateMeetingException e) {
            throw new CommandException(DUPLICATE_MEETING);
        }
    }
}
