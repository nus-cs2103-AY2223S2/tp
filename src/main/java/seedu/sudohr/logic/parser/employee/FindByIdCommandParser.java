package seedu.sudohr.logic.parser.employee;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.logic.commands.employee.FindByIdCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.employee.FindByIdPredicate;
import seedu.sudohr.model.employee.Id;

/**
 * Parses input arguments and creates a new FindCommandById object
 */
public class FindByIdCommandParser implements Parser<FindByIdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByIdCommand
     * and returns a FindByIdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByIdCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMPLOYEE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EMPLOYEE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByIdCommand.MESSAGE_USAGE));
        }

        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_EMPLOYEE).get());
        return new FindByIdCommand(new FindByIdPredicate(id));
    }
}
