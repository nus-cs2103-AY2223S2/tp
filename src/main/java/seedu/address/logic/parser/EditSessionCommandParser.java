package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditSessionCommand object
 */
public class EditSessionCommandParser implements Parser<EditSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSessionCommand
     * and returns an EditSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSessionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SESSION, PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditSessionCommand.MESSAGE_USAGE), pe);
        }

        EditSessionCommand.EditSessionDescriptor editSessionDescriptor = new EditSessionCommand.EditSessionDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editSessionDescriptor.setSessionName(ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SESSION).isPresent()) {
            ArrayList<String> startEnd = ParserUtil.parseSession(argMultimap.getValue(PREFIX_SESSION).get());
            String startDateTime = startEnd.get(0);
            String endDateTime = startEnd.get(1);
            editSessionDescriptor.setStartDateTime(startDateTime);
            editSessionDescriptor.setEndDateTime(endDateTime);
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editSessionDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!editSessionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSessionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditSessionCommand(index, editSessionDescriptor);
    }
}
