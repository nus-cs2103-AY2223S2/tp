package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.stream.Collectors;

import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.DeleteIndexCommand;
import seedu.internship.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteIndexCommandParser implements Parser<DeleteIndexCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteIndexCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = ParserUtil.parseIndexes(args);
            assert indexes != null;
            return new DeleteIndexCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIndexCommand.MESSAGE_USAGE), pe);
        }
    }

    private List<String> parseCompanyNames(List<String> unparsedNames) throws ParseException {
        List<String> parsedNames = ParserUtil.parseCompanyNames(unparsedNames).stream()
                .map(name -> name.fullCompanyName)
                .collect(Collectors.toList());
        return parsedNames;
    }

    private List<String> parseRoles(List<String> unparsedRoles) throws ParseException {
        List<String> parsedRoles = ParserUtil.parseRoles(unparsedRoles).stream()
                .map(role -> role.fullRole)
                .collect(Collectors.toList());
        return parsedRoles;
    }

    private List<String> parseStatuses(List<String> unparsedStatuses) throws ParseException {
        List<String> parsedStatuses = ParserUtil.parseStatuses(unparsedStatuses).stream()
                .map(status -> status.fullStatus)
                .collect(Collectors.toList());
        return parsedStatuses;
    }

    private List<String> parseDates(List<String> unparsedDates) throws ParseException {
        List<String> parsedDates = ParserUtil.parseDates(unparsedDates).stream()
                .map(date -> date.fullDate)
                .collect(Collectors.toList());
        return parsedDates;
    }

    private List<String> parseTags(List<String> unparsedTags) throws ParseException {
        List<String> parsedTags = ParserUtil.parseTags(unparsedTags).stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());
        return parsedTags;
    }

}
