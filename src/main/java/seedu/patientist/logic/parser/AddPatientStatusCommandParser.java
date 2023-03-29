package seedu.patientist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.AddPatientStatusCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.patient.PatientStatusDetails;

/**
 * Parses input arguments and creates a new AddPatientStatusCommand object
 */
public class AddPatientStatusCommandParser implements Parser<AddPatientStatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientStatusCommand
     * and returns an AddPatientStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientStatusCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientStatusCommand.MESSAGE_USAGE), pe);
        }

        ArrayList<PatientStatusDetails> details =
                new ArrayList<>(ParserUtil.parseDetails(argMultimap.getAllValues(PREFIX_STATUS)));
        return new AddPatientStatusCommand(index, details);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
