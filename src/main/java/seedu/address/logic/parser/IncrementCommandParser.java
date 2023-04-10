package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.IncrementCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new IncrementCommand object
 */
public class IncrementCommandParser implements Parser<IncrementCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncrementCommand
     * and returns an IncrementCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncrementCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TRANSACTION_COUNT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncrementCommand.MESSAGE_USAGE), pe);
        }
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_TRANSACTION_COUNT).isPresent()) {
            editPersonDescriptor.setTransactionCount(ParserUtil.parseTransactionCount(
                    argMultimap.getValue(PREFIX_TRANSACTION_COUNT).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(IncrementCommand.MESSAGE_MISSING_INCREMENT_VALUE);
        }

        return new IncrementCommand(index, editPersonDescriptor);
    }

}
