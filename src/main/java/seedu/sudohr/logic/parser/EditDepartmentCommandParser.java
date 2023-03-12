package seedu.sudohr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.EditDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Parses input arguments and creates a new EditDepartmentCommand object
 */
public class EditDepartmentCommandParser implements Parser<EditDepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDepartmentCommand
     * and returns an EditDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDepartmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME);

        DepartmentName name;

        try {
            name = ParserUtil.parseDepartmentName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDepartmentCommand.MESSAGE_USAGE), pe);
        }

        EditDepartmentCommand.EditDepartmentDescriptor editDepartmentDescriptor =
                new EditDepartmentCommand.EditDepartmentDescriptor();
        if (argMultimap.getValue(PREFIX_DEPARTMENT_NAME).isPresent()) {
            editDepartmentDescriptor.setName(ParserUtil.parseDepartmentName(argMultimap
                    .getValue(PREFIX_DEPARTMENT_NAME).get()));
        }

        if (!editDepartmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditDepartmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditDepartmentCommand(name, editDepartmentDescriptor);
    }
}
