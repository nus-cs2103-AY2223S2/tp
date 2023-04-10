package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.util.StringUtil.splitKeywords;
import static arb.logic.parser.ArgumentMultimap.areAllPrefixesPresent;
import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import arb.logic.commands.project.AddProjectCommand;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Name;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {

    //public static provided
    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_PRICE, PREFIX_TAG,
                PREFIX_CLIENT);

        if (!areAllPrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NAME).get());

        Optional<String> deadlineString = argMultimap.getValue(PREFIX_DEADLINE);
        Deadline deadline = null;
        if (deadlineString.isPresent()) {
            deadline = ParserUtil.parseDeadline(deadlineString.get());
        }

        Optional<String> priceString = argMultimap.getValue(PREFIX_PRICE);
        Price price = null;
        if (priceString.isPresent()) {
            price = ParserUtil.parsePrice(priceString.get());
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG).stream()
                .filter(s -> !s.isEmpty()).collect(Collectors.toList()));

        // filter out all invalid client names
        Stream<String> clientNameKeywords = argMultimap.getAllValues(PREFIX_CLIENT).stream()
                .flatMap(s -> splitKeywords(s)).filter(s -> Name.isValidName(s));
        List<String> listOfClientNameKeywords = clientNameKeywords.collect(Collectors.toList());
        Optional<NameContainsKeywordsPredicate> clientNamePredicate = listOfClientNameKeywords.isEmpty()
                ? Optional.empty()
                : Optional.of(new NameContainsKeywordsPredicate(listOfClientNameKeywords));

        Project project = new Project(title, deadline, price, tagList);

        return new AddProjectCommand(project, clientNamePredicate);
    }

}
