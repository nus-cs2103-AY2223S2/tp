package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TODO;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.patientist.logic.commands.AddPatientCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.person.patient.PatientToDo;
import seedu.patientist.model.tag.PriorityTag;
import seedu.patientist.model.tag.Tag;
import seedu.patientist.model.ward.Ward;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddPatientCommandParser implements Parser<AddPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_WARD, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_PRIORITY, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_WARD, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_PRIORITY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        IdNumber idNumber = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Ward ward = ParserUtil.parseWard(argMultimap.getValue(PREFIX_WARD).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        try {
            PriorityTag priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            ArrayList<PatientStatusDetails> details =
                    new ArrayList<>(ParserUtil.parseDetails(argMultimap.getAllValues(PREFIX_STATUS)));
            ArrayList<PatientToDo> toDos =
                    new ArrayList<>(ParserUtil.parseToDos(argMultimap.getAllValues(PREFIX_TODO)));
            Set<Tag> tagList = new HashSet<>(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));
            Patient patient = new Patient(idNumber, name, phone, email, address, priority, details, toDos, tagList);
            return new AddPatientCommand(ward.getWardName(), patient);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
