package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddRoutineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Calorie;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Gender;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Weight;
import seedu.address.model.routines.Exercise;
import seedu.address.model.routines.Routine;
import seedu.address.model.routines.RoutineName;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

/**
 * Parses input arguments and creates a new AddRoutineCommand object
 */
public class AddRoutineCommandParser implements Parser<AddRoutineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoutineCommand
     * and returns an AddRoutineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRoutineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_EXERCISE);
        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoutineCommand.MESSAGE_USAGE));
        }

        RoutineName routineName = ParserUtil.parseRoutineName(argMultimap.getValue(PREFIX_ROUTINE).get());
        Set<Exercise> exerciseList = ParserUtil.parseExercises(argMultimap.getAllValues(PREFIX_EXERCISE));
        Routine routine = new Routine(routineName, exerciseList);
        return new AddRoutineCommand(routine);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
