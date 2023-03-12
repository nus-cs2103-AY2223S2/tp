package seedu.careflow.logic.parser.patientparser;

import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_NUMBER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.logic.parser.ArgumentMultimap;
import seedu.careflow.logic.parser.ArgumentTokenizer;
import seedu.careflow.logic.parser.Parser;
import seedu.careflow.logic.parser.ParserUtil;
import seedu.careflow.logic.parser.Prefix;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.model.person.Address;
import seedu.careflow.model.person.DateOfBirth;
import seedu.careflow.model.person.DrugAllergy;
import seedu.careflow.model.person.Email;
import seedu.careflow.model.person.Gender;
import seedu.careflow.model.person.Ic;
import seedu.careflow.model.person.Name;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.person.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution of adding a patient.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput,
                        PREFIX_NAME,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_ADDRESS,
                        PREFIX_DOB,
                        PREFIX_GENDER,
                        PREFIX_IC,
                        PREFIX_DRUG_ALLERGY,
                        PREFIX_EMERGENCY_CONTACT_NUMBER
                        );

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_DOB, PREFIX_GENDER, PREFIX_IC,
                PREFIX_DRUG_ALLERGY, PREFIX_EMERGENCY_CONTACT_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.careflow.logic.commands.patientcommands.AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        DateOfBirth birthDate = ParserUtil.parseDateOfBirth(
                argMultimap.getValue(PREFIX_DOB).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Ic ic = ParserUtil.parseIc(argMultimap.getValue(PREFIX_IC).get());
        DrugAllergy drugAllergy = ParserUtil.parseDrugAllergy(
                argMultimap.getValue(PREFIX_DRUG_ALLERGY).get());
        Phone emergencyContactNumber = ParserUtil.parsePhone(
                argMultimap.getValue(PREFIX_EMERGENCY_CONTACT_NUMBER).get());
        Patient patient = new Patient(name, phone, email, address, birthDate, gender, ic,
                drugAllergy, emergencyContactNumber);
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
