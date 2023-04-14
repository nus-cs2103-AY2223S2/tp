package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import java.time.LocalDate;

import seedu.sudohr.logic.commands.department.ListDepartmentHeadcountCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;




/**
 * Parses input arguments and creates a new ListDepartmentHeadcountCommand object.
 */
public class ListDepartmentHeadcountCommandParser implements Parser<ListDepartmentHeadcountCommand> {

    @Override
    public ListDepartmentHeadcountCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME, PREFIX_DATE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DEPARTMENT_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListDepartmentHeadcountCommand.MESSAGE_USAGE));
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate givenDate = argMultimap.getValue(PREFIX_DATE).isEmpty()
                ? LocalDate.now()
                : ParserUtil.parseLocalDate(argMultimap.getValue(PREFIX_DATE).get());

        DepartmentName departmentName = ParserUtil.parseDepartmentName(
                argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get()
        );

        return new ListDepartmentHeadcountCommand(currentDate, givenDate, departmentName);
    }
}
