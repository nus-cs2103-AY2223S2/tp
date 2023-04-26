package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignPatientCommand;
import seedu.address.logic.commands.UnassignPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnassignPatientCommand object
 */
public class UnassignPatientCommandParser implements Parser<UnassignPatientCommand> {

    @Override
    public UnassignPatientCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR);

            if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT, PREFIX_DOCTOR)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignPatientCommand.getCommandUsage()));
            }

            Index patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get());
            Index doctorIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DOCTOR).get());
            return new UnassignPatientCommand(patientIndex, doctorIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignPatientCommand.getCommandUsage()), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
