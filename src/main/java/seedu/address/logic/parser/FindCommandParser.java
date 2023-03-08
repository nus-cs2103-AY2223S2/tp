package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

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
        /*String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new InternshipContainsKeywordsPredicate(Arrays.asList(nameKeywords)));*/

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_STATUS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords;
        List<String> statusKeywords;
        List<String> tagKeywords;

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            nameKeywords = split(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        } else {
            nameKeywords = Collections.emptyList();
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            statusKeywords = split(argMultimap.getValue(PREFIX_STATUS).get());
        } else {
            statusKeywords = Collections.emptyList();
        }

        tagKeywords = split(argMultimap.getAllValues(PREFIX_TAG));

        return new FindCommand(new InternshipContainsKeywordsPredicate(nameKeywords, statusKeywords, tagKeywords));
    }

    private List<String> split(String str) throws ParseException {
        if (str.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] keywords = str.split("\\s+");
        return Arrays.asList(keywords);
    }

    private List<String> split(List<String> ls) throws ParseException {
        for (String tag : ls) {
            if (tag.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        return ls.stream()
                .map(tag -> tag.split("\\s+"))
                .flatMap(arr -> Arrays.asList(arr).stream())
                .collect(Collectors.toList());
    }

}
