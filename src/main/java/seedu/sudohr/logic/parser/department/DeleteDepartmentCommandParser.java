package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.department.DeleteDepartmentCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Parses input arguments and creates a new DeleteDepartmentCommand object
 */
public class DeleteDepartmentCommandParser implements Parser<DeleteDepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDepartmentCommand
     * and returns a DeleteDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME);

        if (argMultimap.getValue(PREFIX_DEPARTMENT_NAME).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDepartmentCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_DEPARTMENT_NAME).isPresent();

        String departmentNameInput = argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get();

        DepartmentName departmentName = ParserUtil.parseDepartmentName(departmentNameInput);

        return new DeleteDepartmentCommand(departmentName);
    }
}
