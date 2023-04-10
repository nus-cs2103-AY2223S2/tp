package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_START;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateMeetingCommand;
import seedu.address.logic.commands.UpdateMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateMeetingCommand object
 */
public class UpdateMeetingCommandParser implements Parser<UpdateMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateMeetingCommand
     * and returns an UpdateMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateMeetingCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_MISSING_ARGUMENTS, UpdateMeetingCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_MEETING_DESC, PREFIX_MEETING_START, PREFIX_MEETING_END);

        Index index;
        Index meetingIndex;

        String[] allIndexes = argMultimap.getPreamble().split(" ");

        if (allIndexes.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateMeetingCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(allIndexes[0]);
            meetingIndex = ParserUtil.parseIndex(allIndexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateMeetingCommand.MESSAGE_USAGE),
                pe);
        }
        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_MEETING_DESC).isPresent()) {
            editMeetingDescriptor.setDescription(
                ParserUtil.parseMeetingDescription(argMultimap.getValue(PREFIX_MEETING_DESC).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_START).isPresent()) {
            editMeetingDescriptor.setStart(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_MEETING_START).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_END).isPresent()) {
            editMeetingDescriptor.setEnd(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_MEETING_END).get()));
        }
        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateMeetingCommand(index, meetingIndex, editMeetingDescriptor);
    }

}
