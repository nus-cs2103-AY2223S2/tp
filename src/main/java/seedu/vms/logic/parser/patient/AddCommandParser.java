package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.util.Set;
import java.util.stream.Stream;

import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.Prefix;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(ArgumentMultimap argsMap) throws ParseException {
        if (!arePrefixesPresent(argsMap, PREFIX_NAME, PREFIX_PHONE, PREFIX_DOB, PREFIX_BLOODTYPE)
                || !argsMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argsMap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argsMap.getValue(PREFIX_PHONE).get());
        Dob dateOfBirth = ParserUtil.parseDob(argsMap.getValue(PREFIX_DOB).get());
        BloodType bloodType = ParserUtil.parseBloodType(argsMap.getValue(PREFIX_BLOODTYPE).get());
        Set<GroupName> allergies = ParserUtil.parseGroups(argsMap.getAllValues(PREFIX_ALLERGY));
        Set<GroupName> vaccines = ParserUtil.parseGroups(argsMap.getAllValues(PREFIX_VACCINATION));

        Patient patient = new Patient(name, phone, dateOfBirth, bloodType, allergies, vaccines);

        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
