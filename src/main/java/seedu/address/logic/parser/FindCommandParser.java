package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameAndPhoneContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;




/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);



        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)) {
            //Means both applicant's name and phone number are given

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            String[] nameKeywords = name.fullName.split("\\s+");
            String[] phoneKeywords = phone.value.split("\\s+");
            String[] combinedKeywords = Arrays.copyOf(nameKeywords, nameKeywords.length + phoneKeywords.length);
            System.arraycopy(phoneKeywords, 0, combinedKeywords, nameKeywords.length, phoneKeywords.length);

            return new FindCommand(new NameAndPhoneContainsKeywordsPredicate(Arrays.asList(combinedKeywords)));

        } else {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");


            if (keywords.length == 1) {
                String nameOrPhone = keywords[0];
                char firstChar = nameOrPhone.charAt(0);
                if (Character.isDigit(firstChar)) {
                    return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
