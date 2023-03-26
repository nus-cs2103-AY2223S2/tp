package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;

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
        if (prefix.equals("/g")) {
            return new SortCommand("grade");
        } else if (prefix.equals("/c")) {
            return new SortCommand("credit");
        } else if (prefix.equals("/y")) {
            return new SortCommand("semyear");
        } else {
            throw new ParseException("Invalid format!");
        }

    }
}
