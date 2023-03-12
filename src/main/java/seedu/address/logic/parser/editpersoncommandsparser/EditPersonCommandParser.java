package seedu.address.logic.parser.editpersoncommandsparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Abstract class to inherit from for parser classes which parse objects of the {@link Person} class
 */
public abstract class EditPersonCommandParser {

    public abstract Optional<Index> parseIndex(String index) throws ParseException;

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditPersonDescriptor for edit commands to use.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonDescriptor parseForTags(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_GENDER, PREFIX_MAJOR, PREFIX_MODULES, PREFIX_RACE, PREFIX_COMMS);

        Optional<Index> index;
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        try {
            index = this.parseIndex(argMultimap.getPreamble());
            editPersonDescriptor.setIndex(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE), pe
            );
        }

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
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editPersonDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_RACE).isPresent()) {
            editPersonDescriptor.setRace(ParserUtil.parseRace(argMultimap.getValue(PREFIX_RACE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMS).isPresent()) {
            editPersonDescriptor.setComms(ParserUtil.parseComms(argMultimap.getValue(PREFIX_COMMS).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULES)).ifPresent(editPersonDescriptor::setModules);


        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditContactCommand.MESSAGE_NOT_EDITED);
        }

        return editPersonDescriptor;
    }

    private Optional<Modules> parseModulesForEdit(Collection<String> mods) throws ParseException {
        assert mods != null;

        if (mods.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = mods.size() == 1 && mods.contains("") ? Collections.emptySet() : mods;
        return Optional.of(ParserUtil.parseModules(tagSet));
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

