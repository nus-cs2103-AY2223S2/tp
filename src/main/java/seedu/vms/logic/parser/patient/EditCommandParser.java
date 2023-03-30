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
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.Patient;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditCommand parse(ArgumentMultimap argsMap) throws ParseException {
        requireNonNull(argsMap);

        Index index;

        try {
            index = ParserUtil.parseIndex(argsMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        if (argsMap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argsMap.getValue(PREFIX_NAME).get()));
        }
        if (argsMap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argsMap.getValue(PREFIX_PHONE).get()));
        }
        if (argsMap.getValue(PREFIX_DOB).isPresent()) {
            editPatientDescriptor.setDob(ParserUtil.parseDob(argsMap.getValue(PREFIX_DOB).get()));
        }
        if (argsMap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            editPatientDescriptor.setBloodType(ParserUtil.parseBloodType(argsMap.getValue(PREFIX_BLOODTYPE).get()));
        }
        parseGroupNamesForEdit(argsMap.getAllValues(PREFIX_ALLERGY)).ifPresent(editPatientDescriptor::setAllergies);
        parseGroupNamesForEdit(argsMap.getAllValues(PREFIX_VACCINATION)).ifPresent(editPatientDescriptor::setVaccines);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        Optional<String> errMessage = Patient.validateParams(editPatientDescriptor.getAllergies(),
                editPatientDescriptor.getVaccines());
        if (errMessage.isPresent()) {
            throw new ParseException(errMessage.get());
        }

        boolean isSet = argsMap.getValue(CliSyntax.PREFIX_SET)
                .map(input -> ParserUtil.parseBoolean(input))
                .orElse(false);

        return new EditCommand(index, editPatientDescriptor, isSet);
    }

    /**
     * Parses {@code Collection<String> groupNames} into a {@code Set<GroupName>} if {@code groupNames} is non-empty.
     * If {@code groupNames} contain only one element which is an empty string, it
     * will be parsed into a {@code Set<GroupName>} containing zero allergies.
     */
    private Optional<Set<GroupName>> parseGroupNamesForEdit(Collection<String> groupNames) throws ParseException {
        assert groupNames != null;

        if (groupNames.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> groupNamesSet = groupNames.size() == 1 && groupNames.contains("") ? Collections.emptySet()
                : groupNames;
        return Optional.of(ParserUtil.parseGroups(groupNamesSet));
    }

}
