package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.commons.util.StringUtil.splitKeywords;
import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import arb.commons.core.index.Index;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Name;
import arb.model.client.predicates.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditProjectCommand
     * and returns an EditProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_PRICE, PREFIX_TAG,
                PREFIX_CLIENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE), pe);
        }

        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editProjectDescriptor.setTitle(ParserUtil.parseTitle(argumentMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argumentMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argumentMultimap.getValue(PREFIX_DEADLINE)
                    .get()));
        }

        if (argumentMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editProjectDescriptor.setPrice(ParserUtil.parsePrice(argumentMultimap.getValue(PREFIX_PRICE)
                    .get()));
        }

        ParserUtil.parseTagsForEdit(argumentMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editProjectDescriptor::setTags);

        // filter out all invalid client names
        parseClientNameKeywordsForEdit(argumentMultimap.getAllValues(PREFIX_CLIENT))
                .ifPresent(o -> editProjectDescriptor.setClientNamePredicate(o.orElse(null)));

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProjectCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProjectCommand(index, editProjectDescriptor);
    }

    /**
     * Parses {@code List<String> clientNameKeywords} into an
     * {@code Optional<Optional<NameContainsKeywordsPredicate>>}. If {@code clientNameKeywords} is empty,
     * an empty Optional is returned. If {@code clientNameKeywords} contain only one element which is an
     * empty string, it will be parsed into an Optional containing an empty Optional.
     */
    private Optional<Optional<NameContainsKeywordsPredicate>> parseClientNameKeywordsForEdit(
            List<String> clientNameKeywords) throws ParseException {
        assert clientNameKeywords != null;

        if (clientNameKeywords.isEmpty()) {
            return Optional.empty();
        } else if (clientNameKeywords.size() == 1 && clientNameKeywords.contains("")) {
            return Optional.of(Optional.empty());
        }

        List<String> clientNameKeywordsList = clientNameKeywords.stream().flatMap(s -> splitKeywords(s))
                .collect(Collectors.toList());
        if (clientNameKeywordsList.stream().anyMatch(s -> s.equals(""))) {
            throw new ParseException("There should not be a mix of empty and non-empty client keywords");
        }

        List<String> filteredKeywordsList = clientNameKeywordsList.stream().filter(s -> Name.isValidName(s))
                .collect(Collectors.toList());
        if (filteredKeywordsList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Optional.of(new NameContainsKeywordsPredicate(filteredKeywordsList)));
    }

}
