package seedu.medinfo.logic.parser;

import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.medinfo.logic.commands.AddCommand;
import seedu.medinfo.logic.parser.exceptions.ParseException;
import seedu.medinfo.model.patient.Name;
import seedu.medinfo.model.patient.Nric;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.Status;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddCommand}
     * and returns an {@code AddCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        assert (name != null);
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        assert (nric != null);

        Patient patient;

        if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            assert(status != null);
            patient = new Patient(nric, name, status);
        } else {
            patient = new Patient(nric, name);
        }

        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
