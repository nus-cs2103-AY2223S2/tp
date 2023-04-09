package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.ExitCommand;
import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.commands.HelpCommand;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.logic.commands.TagCommand;
import seedu.modtrek.logic.commands.ViewCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        switch (trimmedArgs.toUpperCase()) {
        case "ADD":
            return new HelpCommand(AddCommand.MESSAGE_USAGE);
        case "EDIT":
            return new HelpCommand(EditCommand.MESSAGE_USAGE);
        case "DELETE":
            return new HelpCommand(DeleteCommand.MESSAGE_USAGE);
        case "TAG":
            return new HelpCommand(TagCommand.MESSAGE_USAGE);
        case "VIEW":
            return new HelpCommand(ViewCommand.MESSAGE_USAGE);
        case "FIND":
            return new HelpCommand(FindCommand.MESSAGE_USAGE);
        case "EXIT":
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);
        case "SORT":
            return new HelpCommand(SortCommand.MESSAGE_USAGE);
        default:
            return new HelpCommand(HelpCommand.SHOWING_ALL_MESSAGE_USAGE);
        }
    }
}
