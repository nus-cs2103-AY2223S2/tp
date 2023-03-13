package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingsCommand;
import seedu.address.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditMeetingsCommand object
 */
public class EditMeetingParser implements Parser<EditMeetingsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingsCommand
     * and returns an EditMeetingsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TIMEDATE,
                                          PREFIX_ATTENDEES, PREFIX_LOCATION, PREFIX_DESCRIPTION);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                    EditMeetingsCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMEDATE).isPresent()) {
            editMeetingDescriptor.setDateTime(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_TIMEDATE).get()));
        }
        if (argMultimap.getValue(PREFIX_ATTENDEES).isPresent()) {
            editMeetingDescriptor.setAttendees(ParserUtil.parseAttendees(argMultimap.getValue(PREFIX_ATTENDEES).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editMeetingDescriptor.setDescription(
                ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }


        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingsCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingsCommand(index, editMeetingDescriptor);
    }


}
