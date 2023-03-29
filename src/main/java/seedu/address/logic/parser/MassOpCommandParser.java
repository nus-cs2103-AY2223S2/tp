package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import seedu.address.logic.commands.MassOpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldsMatchRegexPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new MassOpCommand object
 */
public class MassOpCommandParser implements Parser<MassOpCommand> {
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
     * Parses the given {@code String} of arguments in the context of the MassOpCommand
     * and returns a MassOpCommand object for execution.
     *
     * @param args The arguments to the MassOpCommand
     * @return The parsed MassOpCommand
     * @throws ParseException if {@code args} does not conform the expected format
     */
    public MassOpCommand parse(String args) throws ParseException {
        boolean isDelete = true;
        int tagNameIndex = 1;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_INCOME, PREFIX_TAG);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassOpCommand.MESSAGE_USAGE));
        }

        for (int i = 0; i < TagCommand.commandWords.size(); i++) {
            if (argMultimap.getPreamble().contains(TagCommand.commandWords.get(i))) {
                isDelete = false;
            }
        }

        String[] preamble = argMultimap.getPreamble().split(" ");
        int noOfIndexes = 2;

        if (preamble.length != noOfIndexes) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassOpCommand.MESSAGE_USAGE));
        }

        String tagName = preamble[tagNameIndex];
        Tag tagToAddOrDelete = new Tag(tagName);

        List<String> nameList = formSubstring(argMultimap.getAllValues(PREFIX_NAME));
        List<String> phoneList = formSubstring(argMultimap.getAllValues(PREFIX_PHONE));
        List<String> emailList = formSubstring(argMultimap.getAllValues(PREFIX_EMAIL));
        List<String> addressList = formSubstring(argMultimap.getAllValues(PREFIX_ADDRESS));
        List<String> incomeList = formSubstring(argMultimap.getAllValues(PREFIX_INCOME));
        List<String> tagList = formSubstring(argMultimap.getAllValues(PREFIX_TAG));

        return new MassOpCommand(
                new FieldsMatchRegexPredicate(nameList, phoneList, emailList, addressList, incomeList, tagList),
                tagToAddOrDelete, isDelete);
    }

}
