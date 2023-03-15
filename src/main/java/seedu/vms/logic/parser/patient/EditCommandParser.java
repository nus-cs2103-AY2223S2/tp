package seedu.vms.logic.parser.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.patient.EditCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_DOB).isPresent()) {
            editPatientDescriptor.setDob(ParserUtil.parseDob(argMultimap.getValue(PREFIX_DOB).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            editPatientDescriptor.setBloodType(ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get()));
        }
        parseAllergiesForEdit(argMultimap.getAllValues(PREFIX_ALLERGY)).ifPresent(editPatientDescriptor::setAllergies);
        parseVaccinesForEdit(argMultimap.getAllValues(PREFIX_VACCINATION))
                .ifPresent(editPatientDescriptor::setVaccines);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPatientDescriptor);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<GroupName>} if
     * {@code allergies} is non-empty.
     * If {@code allergies} contain only one element which is an empty string, it
     * will be parsed into a
     * {@code Set<GroupName>} containing zero allergies.
     */
    private Optional<Set<GroupName>> parseAllergiesForEdit(Collection<String> allergies) throws ParseException {
        assert allergies != null;

        if (allergies.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> allergySet = allergies.size() == 1 && allergies.contains("") ? Collections.emptySet()
                : allergies;
        return Optional.of(ParserUtil.parseGroups(allergySet));
    }

    /**
     * Parses {@code Collection<String> vaccines} into a {@code Set<GroupName>} if
     * {@code vaccines} is non-empty.
     * If {@code vaccines} contain only one element which is an empty string, it
     * will be parsed into a
     * {@code Set<GroupName>} containing zero vaccines.
     */
    private Optional<Set<GroupName>> parseVaccinesForEdit(Collection<String> vaccines) throws ParseException {
        assert vaccines != null;

        if (vaccines.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> vaccineSet = vaccines.size() == 1 && vaccines.contains("") ? Collections.emptySet()
                : vaccines;
        return Optional.of(ParserUtil.parseGroups(vaccineSet));
    }

}
