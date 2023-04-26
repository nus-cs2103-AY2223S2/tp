package seedu.address.logic.parser;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteDoctorCommand object
 */
public class DeleteDoctorCommandParser implements Parser<DeleteDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteDoctorCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDoctorCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format("%s\n%s", pe.getMessage(), DeleteDoctorCommand.getCommandUsage()), pe);
        }
    }
}
