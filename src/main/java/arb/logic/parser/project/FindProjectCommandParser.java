package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.core.Messages.MESSAGE_NO_VALID_PARAMETERS;
import static arb.commons.util.StringUtil.splitKeywords;
import static arb.logic.parser.ArgumentMultimap.areAnyPrefixesPresent;
import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_END;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_START;
import static arb.logic.parser.CliSyntax.PREFIX_STATUS;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.project.FindProjectCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Name;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.model.project.predicates.IsOfStatusPredicate;
import arb.model.project.predicates.LinkedClientNameContainsKeywordsPredicate;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.ProjectWithinTimeframePredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;
import arb.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindProjectCommand object
 */
public class FindProjectCommandParser implements Parser<FindProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProjectCommand
     * and returns a FindProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_STATUS, PREFIX_START, PREFIX_END, PREFIX_TAG, PREFIX_CLIENT);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_START,
                PREFIX_END, PREFIX_TAG, PREFIX_CLIENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Project>> predicates = new ArrayList<>();

        // filter out all invalid tags
        Stream<String> tags = argMultimap.getAllValues(PREFIX_TAG).stream().flatMap(s -> splitKeywords(s))
                .filter(s -> Tag.isValidTagName(s));
        List<String> listOfTags = tags.collect(Collectors.toList());
        if (!listOfTags.isEmpty()) {
            predicates.add(new ProjectContainsTagsPredicate(listOfTags));
        }

        // filter out all invalid titles
        Stream<String> titleKeywords = argMultimap.getAllValues(PREFIX_NAME).stream().flatMap(s -> splitKeywords(s))
                .filter(s -> Title.isValidTitle(s));
        List<String> listOfTitleKeywords = titleKeywords.collect(Collectors.toList());
        if (!listOfTitleKeywords.isEmpty()) {
            predicates.add(new TitleContainsKeywordsPredicate(listOfTitleKeywords));
        }

        // filter out all invalid client names
        Stream<String> clientNameKeywords = argMultimap.getAllValues(PREFIX_CLIENT).stream()
                .flatMap(s -> splitKeywords(s)).filter(s -> Name.isValidName(s));
        List<String> listOfClientNameKeywords = clientNameKeywords.collect(Collectors.toList());
        if (!listOfClientNameKeywords.isEmpty()) {
            predicates.add(new LinkedClientNameContainsKeywordsPredicate(listOfClientNameKeywords));
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

        if (startOfTimeframe != null && endOfTimeframe != null) {
            if (endOfTimeframe.compareTo(startOfTimeframe) < 0) {
                throw new ParseException(FindProjectCommand.MESSAGE_END_BEFORE_START_ERROR);
            }
        }

        // if at least one of the timeframe specifiers are present
        if (startString.isPresent() || endString.isPresent()) {
            predicates.add(new ProjectWithinTimeframePredicate(startOfTimeframe, endOfTimeframe));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(MESSAGE_NO_VALID_PARAMETERS);
        }

        return new FindProjectCommand(new CombinedPredicate<>(predicates));
    }

}
