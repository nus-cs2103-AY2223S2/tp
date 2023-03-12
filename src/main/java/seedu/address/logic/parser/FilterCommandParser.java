package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldsMatchRegexPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    public static final String MESSAGE_INVALID_REGEX = "Invalid regex: %1$s\n(becomes: %2$s)";

    private List<String> formSubstring(List<String> regexes) throws ParseException {
        List<String> newRegexes = new ArrayList<>();
        for (String regex: regexes) {
            try {
                Pattern.compile(".*" + regex + ".*");
                newRegexes.add(".*" + regex + ".*");
            } catch (PatternSyntaxException ex) {
                throw new ParseException(String.format(MESSAGE_INVALID_REGEX, regex, ".*" + regex + ".*"));
            }
        }
        return newRegexes;
    }

    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        List<String> nameList = formSubstring(argMultimap.getAllValues(PREFIX_NAME));
        List<String> phoneList = formSubstring(argMultimap.getAllValues(PREFIX_PHONE));
        List<String> emailList = formSubstring(argMultimap.getAllValues(PREFIX_EMAIL));
        List<String> addressList = formSubstring(argMultimap.getAllValues(PREFIX_ADDRESS));
        List<String> tagList = formSubstring(argMultimap.getAllValues(PREFIX_TAG));
        List<String> remarkList = formSubstring(argMultimap.getAllValues(PREFIX_REMARK));

        return new FilterCommand(
                new FieldsMatchRegexPredicate(nameList, phoneList, emailList, addressList, tagList, remarkList));
    }

}
