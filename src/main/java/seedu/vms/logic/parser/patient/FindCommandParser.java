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

import seedu.vms.logic.commands.patient.FindCommand;
import seedu.vms.logic.commands.patient.FindCommand.FindPatientDescriptor;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindCommand parse(ArgumentMultimap argsMap) throws ParseException {
        requireNonNull(argsMap);

        Boolean flagsPresent = false;

        FindPatientDescriptor findPatientDescriptor = new FindPatientDescriptor();
        if (argsMap.getValue(PREFIX_NAME).isPresent()) {
            flagsPresent = true;
            findPatientDescriptor.setNameSearch(ParserUtil.parseName(argsMap.getValue(PREFIX_NAME).get()).toString());
        }
        if (argsMap.getValue(PREFIX_PHONE).isPresent()) {
            flagsPresent = true;
            findPatientDescriptor.setPhone(ParserUtil.parsePhone(argsMap.getValue(PREFIX_PHONE).get()));
        }
        if (argsMap.getValue(PREFIX_DOB).isPresent()) {
            flagsPresent = true;
            findPatientDescriptor.setDob(ParserUtil.parseDob(argsMap.getValue(PREFIX_DOB).get()));
        }
        if (argsMap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            flagsPresent = true;
            findPatientDescriptor.setBloodType(ParserUtil.parseBloodType(argsMap.getValue(PREFIX_BLOODTYPE).get()));
        }
        if (argsMap.getValue(PREFIX_ALLERGY).isPresent()) {
            flagsPresent = true;
            parseGroupNamesForFind(argsMap.getAllValues(PREFIX_ALLERGY))
                    .ifPresent(findPatientDescriptor::setAllergies);
        }
        if (argsMap.getValue(PREFIX_VACCINATION).isPresent()) {
            flagsPresent = true;
            parseGroupNamesForFind(argsMap.getAllValues(PREFIX_VACCINATION))
                    .ifPresent(findPatientDescriptor::setVaccines);
        }

        if (!flagsPresent) {
            String trimmedArgs = argsMap.getPreamble().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            findPatientDescriptor.setNameSearch(trimmedArgs);
        }

        return new FindCommand(findPatientDescriptor);
    }

    /**
     * Parses {@code Collection<String> groupNames} into a {@code Set<GroupName>} if {@code groupNames} is non-empty.
     * If {@code groupNames} contain only one element which is an empty string, it
     * will be parsed into a {@code Set<GroupName>} containing zero allergies.
     */
    private Optional<Set<GroupName>> parseGroupNamesForFind(Collection<String> groupNames) throws ParseException {
        assert groupNames != null;

        if (groupNames.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> groupNamesSet = groupNames.size() == 1 && groupNames.contains("") ? Collections.emptySet()
                : groupNames;
        return Optional.of(ParserUtil.parseGroups(groupNamesSet));
    }

}
