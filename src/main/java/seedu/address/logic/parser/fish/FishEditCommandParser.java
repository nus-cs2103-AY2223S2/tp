package seedu.address.logic.parser.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDING_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishEditCommand;
import seedu.address.logic.commands.fish.FishEditCommand.EditFishDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FishEditCommand object
 */
public class FishEditCommandParser implements Parser<FishEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FishEditCommand
     * and returns an FishEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FishEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LAST_FED_DATE, PREFIX_SPECIES,
                        PREFIX_FEEDING_INTERVAL,
                        PREFIX_TANK, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishEditCommand.MESSAGE_USAGE), pe);
        }

        EditFishDescriptor editFishDescriptor = new EditFishDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editFishDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_LAST_FED_DATE).isPresent()) {
            editFishDescriptor.setLastFedDate(ParserUtil.parseLastFedDate(argMultimap.getValue(PREFIX_LAST_FED_DATE)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_SPECIES).isPresent()) {
            editFishDescriptor.setSpecies(ParserUtil.parseSpecies(argMultimap.getValue(PREFIX_SPECIES).get()));
        }
        if (argMultimap.getValue(PREFIX_FEEDING_INTERVAL).isPresent()) {
            editFishDescriptor.setFeedingInterval(ParserUtil.parseFeedingInterval(argMultimap
                    .getValue(PREFIX_FEEDING_INTERVAL).get()));
        }
        if (argMultimap.getValue(PREFIX_TANK).isPresent()) {
            editFishDescriptor.setTankIndex(ParserUtil.parseTank(argMultimap.getValue(PREFIX_TANK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editFishDescriptor::setTags);

        if (!editFishDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FishEditCommand.MESSAGE_NOT_EDITED);
        }

        return new FishEditCommand(index, editFishDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
