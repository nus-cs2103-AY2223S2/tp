package seedu.task.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.DeadlineDateContainsKeywordsPredicate;
import seedu.task.model.task.DescContainsAllKeywordsPredicate;
import seedu.task.model.task.DescContainsKeywordsPredicate;
import seedu.task.model.task.Description;
import seedu.task.model.task.EventFromContainsKeywordsPredicate;
import seedu.task.model.task.EventToContainsKeywordsPredicate;
import seedu.task.model.task.Name;
import seedu.task.model.task.NameContainsAllKeywordsPredicate;
import seedu.task.model.task.NameContainsKeywordsPredicate;
import seedu.task.model.task.SameEffortPredicate;
import seedu.task.model.task.TagsContainsAllKeywordsPredicate;
import seedu.task.model.task.TagsContainsKeywordsPredicate;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DESCRIPTION,
                        CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_ALLMATCH,
                    CliSyntax.PREFIX_DEADLINE, CliSyntax.PREFIX_FROM, CliSyntax.PREFIX_TO, CliSyntax.PREFIX_EFFORT);

        if (areTooManyPrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DESCRIPTION,
                CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_DEADLINE, CliSyntax.PREFIX_FROM, CliSyntax.PREFIX_TO)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_NAME, argMultimap);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_DESCRIPTION, argMultimap);
        }
        if (parseTagsForFind(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_TAG, argMultimap);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DEADLINE).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_DEADLINE, argMultimap);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_FROM).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_FROM, argMultimap);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_TO).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_TO, argMultimap);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EFFORT).isPresent()) {
            return parseForFindCommand(CliSyntax.PREFIX_EFFORT, argMultimap);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if more than 1 prefix is present in the given {@code ArgumentMultimap}.
     */
    private static boolean areTooManyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForFind(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private FindCommand parseForFindCommand(Prefix prefix, ArgumentMultimap argMultimap)
            throws ParseException {
        if (prefix.equals(CliSyntax.PREFIX_NAME)) {
            Set<Name> names = ParserUtil.parseNames(argMultimap.getAllValues(CliSyntax.PREFIX_NAME));
            Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            if (argMultimap.getValue(CliSyntax.PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new NameContainsAllKeywordsPredicate(ParserUtil.parseNamesToList(names)));
            }
            return new FindCommand(new NameContainsKeywordsPredicate(name.toString()));
        } else if (prefix.equals(CliSyntax.PREFIX_DESCRIPTION)) {
            Set<Description> descriptions = ParserUtil.parseDescriptions(
                    argMultimap.getAllValues(CliSyntax.PREFIX_DESCRIPTION));
            Description description = ParserUtil.parseDescription(
                    argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
            if (argMultimap.getValue(CliSyntax.PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new DescContainsAllKeywordsPredicate(ParserUtil
                    .parseDescriptionsToList(descriptions)));
            }
            return new FindCommand(new DescContainsKeywordsPredicate(description.toString()));
        } else if (prefix.equals(CliSyntax.PREFIX_TAG)) {
            Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
            if (argMultimap.getValue(CliSyntax.PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new TagsContainsAllKeywordsPredicate(ParserUtil.parseTagsToList(tags)));
            }
            return new FindCommand(new TagsContainsKeywordsPredicate(ParserUtil.parseTagsToList(tags)));
        } else if (prefix.equals(CliSyntax.PREFIX_DEADLINE)) {
            String date = Date.parseFindDate(argMultimap.getValue(CliSyntax.PREFIX_DEADLINE).get());
            return new FindCommand(new DeadlineDateContainsKeywordsPredicate(date));
        } else if (prefix.equals(CliSyntax.PREFIX_FROM)) {
            String date = Date.parseFindDate(argMultimap.getValue(CliSyntax.PREFIX_FROM).get());
            return new FindCommand(new EventFromContainsKeywordsPredicate(date));
        } else if (prefix.equals(CliSyntax.PREFIX_TO)) {
            String date = Date.parseFindDate(argMultimap.getValue(CliSyntax.PREFIX_TO).get());
            return new FindCommand(new EventToContainsKeywordsPredicate(date));
        } else if (prefix.equals(CliSyntax.PREFIX_EFFORT)) {
            String effort = argMultimap.getValue(CliSyntax.PREFIX_EFFORT).get();
            return new FindCommand(new SameEffortPredicate(ParserUtil.parseEffort(effort)));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
