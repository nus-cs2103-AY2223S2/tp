package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.sudohr.logic.commands.department.FindDepartmentCommand;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindDepartmentCommand object
 */
public class FindDepartmentCommandParser implements Parser<FindDepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindDepartmentCommand
     * and returns a FindDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDepartmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDepartmentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDepartmentCommand(new DepartmentNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
