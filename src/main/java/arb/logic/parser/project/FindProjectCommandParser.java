package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_END;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_STATUS;
import static arb.logic.parser.CliSyntax.PREFIX_START;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.project.FindProjectCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.Prefix;
import arb.logic.parser.exceptions.ParseException;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.predicates.IsOfStatusPredicate;
import arb.model.project.predicates.LinkedClientNameContainsKeywordsPredicate;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.ProjectWithinTimeframePredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;

import javax.swing.text.html.Option;

/**
 * Parses input arguments and creates a new FindProjectCommand object
 */
public class FindProjectCommandParser implements Parser<FindProjectCommand> {

    public static final String EMPTY_NAME_ERROR = "Name cannot be empty!";
    public static final String EMPTY_TAG_ERROR = "Tag cannot be empty!";
    public static final String EMPTY_CLIENT_NAME_ERROR = "Client name cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the FindProjectCommand
     * and returns a FindProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_STATUS, PREFIX_START, PREFIX_END, PREFIX_TAG, PREFIX_CLIENT);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_START, PREFIX_END, PREFIX_TAG, PREFIX_CLIENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Project>> predicates = new ArrayList<>();

        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (tags.stream().anyMatch(t -> t.isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_TAG_ERROR));
        }
        if (!tags.isEmpty()) {
            predicates.add(new ProjectContainsTagsPredicate(tags));
        }

        List<String> titleKeywords = argMultimap.getAllValues(PREFIX_NAME);
        if (titleKeywords.stream().anyMatch(t -> t.isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_NAME_ERROR));
        }
        if (!titleKeywords.isEmpty()) {
            predicates.add(new TitleContainsKeywordsPredicate(titleKeywords));
        }

        List<String> clientNameKeywords = argMultimap.getAllValues(PREFIX_CLIENT);
        if (clientNameKeywords.stream().anyMatch(c -> c.isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_CLIENT_NAME_ERROR));
        }
        if (!clientNameKeywords.isEmpty()) {
            predicates.add(new LinkedClientNameContainsKeywordsPredicate(clientNameKeywords));
        }

        Optional<String> statusString = argMultimap.getValue(PREFIX_STATUS);
        Status status = null;
        if (statusString.isPresent()) {
            status = ParserUtil.parseStatus(statusString.get());
        }
        if (status != null) {
            predicates.add(new IsOfStatusPredicate(status));
        }

        Optional<String> startString = argMultimap.getValue(PREFIX_START);
        Deadline startOfTimeframe = null;
        if (startString.isPresent()) {
            startOfTimeframe = ParserUtil.parseDeadline(startString.get());
        }

        Optional<String> endString = argMultimap.getValue(PREFIX_END);
        Deadline endOfTimeframe = null;
        if (endString.isPresent()) {
            endOfTimeframe = ParserUtil.parseDeadline(endString.get());
        }

        if (startString.isPresent() || endString.isPresent()) {
            predicates.add(new ProjectWithinTimeframePredicate(startOfTimeframe, endOfTimeframe));
        }

        return new FindProjectCommand(new CombinedPredicate<>(predicates));
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
