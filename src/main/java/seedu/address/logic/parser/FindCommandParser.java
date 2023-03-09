package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicate.PersonContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PROFILE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_LANGUAGE, PREFIX_TAG);

        // ensure no text before first valid prefix
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> nameArguments = argMultimap.getAllValues(PREFIX_NAME);
        List<String> profileArguments = argMultimap.getAllValues(PREFIX_PROFILE);
        List<String> phoneArguments = argMultimap.getAllValues(PREFIX_PHONE);
        List<String> emailArguments = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> addressArguments = argMultimap.getAllValues(PREFIX_ADDRESS);
        List<String> languageArguments = argMultimap.getAllValues(PREFIX_LANGUAGE);
        List<String> tagArguments = argMultimap.getAllValues(PREFIX_TAG);

        PersonContainsKeywordsPredicate personPredicate
                = new PersonContainsKeywordsPredicate(nameArguments,
                profileArguments,
                phoneArguments,
                emailArguments,
                addressArguments,
                languageArguments,
                tagArguments);

        return new FindCommand(personPredicate);
    }

}
