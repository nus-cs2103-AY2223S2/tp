package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_STATUS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_ROLE).isPresent()
                && !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> names = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        List<String> statuses = new ArrayList<>();

        List<String> nameKeywords;
        List<String> roleKeywords;
        List<String> statusKeywords;
        List<String> tagKeywords;

        List<String> nameList = argMultimap.getAllValues(PREFIX_COMPANY_NAME);
        for (String name : nameList) {
            names.add(ParserUtil.parseCompanyName(name).fullCompanyName);
        }
        nameKeywords = this.split(names);

        List<String> roleList = argMultimap.getAllValues(PREFIX_ROLE);
        for (String role : roleList) {
            roles.add(ParserUtil.parseRole(role).fullRole);
        }
        roleKeywords = this.split(roles);

        List<String> statusList = argMultimap.getAllValues(PREFIX_STATUS);
        for (String status : statusList) {
            statuses.add(ParserUtil.parseStatus(status).fullStatus);
        }
        statusKeywords = this.split(statuses);

        List<String> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)).stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());

        tagKeywords = this.split(tags);

        return new FindCommand(new InternshipContainsKeywordsPredicate(nameKeywords, roleKeywords, statusKeywords,
                tagKeywords));
    }

    private List<String> split(List<String> ls) throws ParseException {
        return ls.stream()
                .map(str -> str.split("\\s+"))
                .flatMap(arr -> Arrays.asList(arr).stream())
                .collect(Collectors.toList());
    }

}
