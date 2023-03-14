package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldsMatchRegexPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    public static final String MESSAGE_INVALID_REGEX = "Invalid regex: %1$s\n(becomes: %2$s)";

    /**
     * Converts each regex in <code>regexes</code> from matching a full string to matching a substring.
     *
     * @param regexes Regexes to convert
     * @return Resulting substring-match regexes
     * @throws ParseException if any resulting regex is not a valid regular expression
     */
    private List<String> formSubstring(List<String> regexes) throws ParseException {
        List<String> newRegexes = new ArrayList<>();
        for (String regex : regexes) {
            try {
                Pattern.compile(".*" + regex + ".*");
                newRegexes.add(".*" + regex + ".*");
            } catch (PatternSyntaxException ex) {
                throw new ParseException(String.format(MESSAGE_INVALID_REGEX, regex, ".*" + regex + ".*"));
            }
        }
        return newRegexes;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @param args The arguments to the FilterCommand
     * @return The parsed FilterCommand
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        List<String> nameList = formSubstring(argMultimap.getAllValues(PREFIX_NAME));
        List<String> phoneList = formSubstring(argMultimap.getAllValues(PREFIX_PHONE));
        List<String> emailList = formSubstring(argMultimap.getAllValues(PREFIX_EMAIL));
        List<String> addressList = formSubstring(argMultimap.getAllValues(PREFIX_ADDRESS));
        List<String> tagList = formSubstring(argMultimap.getAllValues(PREFIX_TAG));

        return new FilterCommand(
                new FieldsMatchRegexPredicate(nameList, phoneList, emailList, addressList, tagList));
    }

}
