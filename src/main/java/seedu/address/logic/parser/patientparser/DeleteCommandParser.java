package seedu.address.logic.parser.patientparser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.patientcommands.DeleteCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Ic;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution of deleting a patient.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteCommand parse(String userInput) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_IC);
            if (arePrefixesPresent(argMultimap, PREFIX_IC)) {
                if (argMultimap.getPreamble().isEmpty()) {
                    Ic ic = ParserUtil.parseIc(argMultimap.getValue(PREFIX_IC).get());
                    return new DeleteCommand(ic);
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, seedu.address.logic.commands.DeleteCommand.MESSAGE_USAGE));
                }
            } else {
                Index index = ParserUtil.parseIndex(userInput);
                return new DeleteCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, seedu.address.logic.commands.DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values  in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
