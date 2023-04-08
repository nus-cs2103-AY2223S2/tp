package seedu.medinfo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_CAPACITY;

import seedu.medinfo.commons.core.index.Index;
import seedu.medinfo.logic.commands.EditWardCommand;
import seedu.medinfo.logic.commands.EditWardCommand.EditWardDescriptor;
import seedu.medinfo.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditWardCommandParser implements Parser<EditWardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditWardCommand
     * and returns an EditWardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditWardCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WARD, PREFIX_CAPACITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditWardCommand.MESSAGE_USAGE), pe);
        }

        EditWardDescriptor editWardDescriptor = new EditWardCommand.EditWardDescriptor();
        if (argMultimap.getValue(PREFIX_WARD).isPresent()) {
            if (index.getOneBased() == 1) {
                throw new ParseException(EditWardCommand.MESSAGE_WAITING_ROOM_NAME_EDIT);
            } else {
                editWardDescriptor.setWard(ParserUtil.parseWardName(argMultimap.getValue(PREFIX_WARD).get()));
            }
        }
        if (argMultimap.getValue(PREFIX_CAPACITY).isPresent()) {
            editWardDescriptor.setCapacity(ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get()));
        }

        if (!editWardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditWardCommand.MESSAGE_NOT_EDITED);
        }

        return new EditWardCommand(index, editWardDescriptor);
    }

}
