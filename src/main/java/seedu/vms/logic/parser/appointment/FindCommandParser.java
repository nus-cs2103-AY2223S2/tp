package seedu.vms.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.vms.logic.commands.appointment.FindCommand;
import seedu.vms.logic.commands.appointment.FindCommand.FindAppointmentDescriptor;
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

        boolean flagsPresent = false;

        FindAppointmentDescriptor findAppointmentDescriptor = new FindAppointmentDescriptor();
        if (argsMap.getValue(PREFIX_PATIENT).isPresent()) {
            flagsPresent = true;
            findAppointmentDescriptor.setPatient(ParserUtil.parseIndex(argsMap.getValue(PREFIX_PATIENT).get()));
        }
        if (argsMap.getValue(PREFIX_STARTTIME).isPresent()) {
            flagsPresent = true;
            findAppointmentDescriptor
                    .setAppointmentTime(ParserUtil.parseDate(argsMap.getValue(PREFIX_STARTTIME).get()));
        }
        if (argsMap.getValue(PREFIX_ENDTIME).isPresent()) {
            flagsPresent = true;
            findAppointmentDescriptor.setAppointmentEndTime(ParserUtil
                    .parseDate(argsMap.getValue(PREFIX_ENDTIME).get()));
        }
        if (argsMap.getValue(PREFIX_VACCINATION).isPresent()) {
            flagsPresent = true;
            parseGroupNamesForEdit(argsMap.getAllValues(PREFIX_VACCINATION))
                    .ifPresent(findAppointmentDescriptor::setVaccination);
        }
        if (argsMap.getValue(PREFIX_COMPLETED).isPresent()) {
            flagsPresent = true;
            findAppointmentDescriptor.setStatus(ParserUtil.parseBoolean(argsMap.getValue(PREFIX_COMPLETED).get()));
        }

        if (!flagsPresent) {
            String trimmedArgs = argsMap.getPreamble().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            } else if (ParserUtil.isValidDateTimeFormat(trimmedArgs)) {
                findAppointmentDescriptor.setAppointmentTime(ParserUtil.parseDate(trimmedArgs));
            } else {
                parseGroupNamesForEdit(Collections.singleton(trimmedArgs))
                        .ifPresent(findAppointmentDescriptor::setVaccination);
            }
        }

        return new FindCommand(findAppointmentDescriptor);
    }

    /**
     * Parses {@code Collection<String> groupNames} into a {@code Set<GroupName>} if {@code groupNames} is non-empty.
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
