package seedu.patientist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.AddPatientToDoCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.patient.PatientToDo;

/**
 * Parses input arguments and creates a new AddPatientToDo object
 */
public class AddPatientToDoCommandParser implements Parser<AddPatientToDoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientToDoCommand
     * and returns an AddPatientToDoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientToDoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TODO);

        if (!arePrefixesPresent(argMultimap, PREFIX_TODO) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientToDoCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientToDoCommand.MESSAGE_USAGE), pe);
        }

        ArrayList<PatientToDo> toDos =
                new ArrayList<>(ParserUtil.parseToDos(argMultimap.getAllValues(PREFIX_TODO)));
        return new AddPatientToDoCommand(index, toDos);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
