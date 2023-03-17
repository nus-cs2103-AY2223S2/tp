package seedu.sudohr.logic.parser;

import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.DeleteDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Parses input arguments and creates a new AddDepartmentCommand object
 */
public class DeleteDepartmentCommandParser implements Parser<DeleteDepartmentCommand> {

    public DeleteDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME);

        DepartmentName name = ParserUtil.parseDepartmentName(argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        return new DeleteDepartmentCommand(name);


    }


}
