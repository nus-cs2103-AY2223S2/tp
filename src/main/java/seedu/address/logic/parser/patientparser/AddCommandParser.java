package seedu.address.logic.parser.patientparser;

import seedu.address.logic.commands.patientcommands.AddCommand;


import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.DateOfBirth;
import seedu.address.model.person.patient.DrugAllergy;
import seedu.address.model.person.patient.EmergencyContact;
import seedu.address.model.person.patient.Gender;
import seedu.address.model.person.patient.Ic;


import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

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
                        PREFIX_EMERGENCY_CONTACT_NAME,
                        PREFIX_EMERGENCY_CONTACT_PHONE,
                        PREFIX_EMERGENCY_CONTACT_RELATIONSHIP
                        );
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_DOB, PREFIX_GENDER, PREFIX_IC, PREFIX_DRUG_ALLERGY,
                PREFIX_EMERGENCY_CONTACT_NAME, PREFIX_EMERGENCY_CONTACT_PHONE, PREFIX_EMERGENCY_CONTACT_RELATIONSHIP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, seedu.address.logic.commands.AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        DateOfBirth birthDate = ParserUtil.parseDateOfBirth(argMultimap.
                getValue(PREFIX_DOB).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Ic ic = ParserUtil.parseIc(argMultimap.getValue(PREFIX_IC).get());
        DrugAllergy drugAllergy = ParserUtil.parseDrugAllergy(argMultimap.
                getValue(PREFIX_DRUG_ALLERGY).get());

        // Emergency Contact
        Name emergencyContactName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT_NAME).get());
        Phone emergencyContactPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT_PHONE).get());
        Person emergencyContactPerson = new Person(emergencyContactName, emergencyContactPhone);
        EmergencyContact emergencyContact = ParserUtil.parseEmergencyContact(emergencyContactPerson,
                argMultimap.getValue(PREFIX_EMERGENCY_CONTACT_RELATIONSHIP).get());
        Patient patient = new Patient(name, phone, email, address, birthDate, gender, ic, drugAllergy, emergencyContact);
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
