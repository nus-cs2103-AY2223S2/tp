package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_LECTURE);

        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            try {
                ModuleCode moduleCode = new ModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
                if (argMultimap.getValue(PREFIX_LECTURE).isPresent()) {
                    LectureName lectureName = new LectureName(argMultimap.getValue(PREFIX_LECTURE).get());
                    return new ListCommand(moduleCode, lectureName);
                }
                return new ListCommand(moduleCode);
            } catch (IllegalArgumentException e) {
                return new ListCommand(e);
            }
        }
        return new ListCommand();
    }

}
