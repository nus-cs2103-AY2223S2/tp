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

import seedu.address.commons.util.StringUtil;
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

        List<String> nameArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_NAME).orElse(""));
        List<String> profileArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_PROFILE).orElse(""));
        List<String> phoneArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_PHONE).orElse(""));
        List<String> emailArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        List<String> addressArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_ADDRESS).orElse(""));
        List<String> languageArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_LANGUAGE).orElse(""));
        List<String> tagArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_TAG).orElse(""));

        PersonContainsKeywordsPredicate personPredicate =
                new PersonContainsKeywordsPredicate(nameArguments,
                profileArguments,
                phoneArguments,
                emailArguments,
                addressArguments,
                languageArguments,
                tagArguments);

        return new FindCommand(personPredicate);
    }

}
