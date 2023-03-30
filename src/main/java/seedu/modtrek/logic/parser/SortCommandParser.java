package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;

/**
 * The type Sort command parser.
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String prefix = args.strip();
        switch (prefix) {
        case "/g":
            return new SortCommand("grade");
        case "/c":
            return new SortCommand("credits");
        case "/y":
            return new SortCommand("year");
        case "/m":
            return new SortCommand("code");
        case "/t":
            return new SortCommand("tag");
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
