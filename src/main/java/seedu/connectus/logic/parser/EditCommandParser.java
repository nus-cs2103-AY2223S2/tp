package seedu.connectus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.EditCommand;
import seedu.connectus.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_MODULE,
            PREFIX_SOCMED_INSTAGRAM, PREFIX_SOCMED_TELEGRAM, PREFIX_SOCMED_WHATSAPP, PREFIX_CCA, PREFIX_CCA_POSITION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            editPersonDescriptor.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }
        {
            editPersonDescriptor.setSocialMedia(ParserUtil.parseSocialMedia(argMultimap));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULE)).ifPresent(editPersonDescriptor::setModules);
        parseCcasForEdit(argMultimap.getAllValues(PREFIX_CCA)).ifPresent(editPersonDescriptor::setCcas);
        parseCcaPositionsForEdit(argMultimap.getAllValues(PREFIX_CCA_POSITION))
                .ifPresent(editPersonDescriptor::setCcaPositions);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if
     * {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be
     * parsed into a
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

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<Module>} if
     * {@code modules} is non-empty.
     * If {@code modules} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<module>} containing zero modules.
     */
    private Optional<Set<Module>> parseModulesForEdit(Collection<String> modules) throws ParseException {
        assert modules != null;

        if (modules.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleSet = modules.size() == 1 && modules.contains("") ? Collections.emptySet() : modules;
        return Optional.of(ParserUtil.parseModules(moduleSet));
    }

    /**
     * Parses {@code Collection<String> ccas} into a {@code Set<Cca>} if
     * {@code ccas} is non-empty.
     * If {@code ccas} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Cca>} containing zero ccas.
     */
    private Optional<Set<Cca>> parseCcasForEdit(Collection<String> ccas) throws ParseException {
        assert ccas != null;

        if (ccas.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> ccaSet = ccas.size() == 1 && ccas.contains("") ? Collections.emptySet() : ccas;
        return Optional.of(ParserUtil.parseCcas(ccaSet));
    }

    /**
     * Parses {@code Collection<String> ccaPositions} into a {@code Set<CcaPosition>} if
     * {@code ccaPositions} is non-empty.
     * If {@code ccaPositions} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Cca>} containing zero ccaPositions.
     */
    private Optional<Set<CcaPosition>> parseCcaPositionsForEdit(Collection<String> ccaPositions) throws ParseException {
        assert ccaPositions != null;

        if (ccaPositions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> ccaPositionSet = ccaPositions.size() == 1 && ccaPositions
                .contains("") ? Collections.emptySet() : ccaPositions;
        return Optional.of(ParserUtil.parseCcaPositions(ccaPositionSet));
    }
}
