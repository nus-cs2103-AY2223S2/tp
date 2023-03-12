package seedu.fitbook.logic.parser;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GOAL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Goal;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;
import seedu.fitbook.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_APPOINTMENT, PREFIX_WEIGHT, PREFIX_GENDER, PREFIX_CALORIE, PREFIX_GOAL, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_WEIGHT,
                PREFIX_GENDER, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Appointment> appointmentList = ParserUtil.parseAppointments(argMultimap.getAllValues(PREFIX_APPOINTMENT));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Calorie calorie = optionalPresentCaloriePrefix(argMultimap);
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Goal goal = ParserUtil.parseGoal(argMultimap.getValue(PREFIX_GOAL).get());
        Client client = new Client(name, phone, email, address, appointmentList, weight, gender, calorie, goal,
                tagList);
        return new AddCommand(client);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the Calorie if the prefix is not empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * @param argumentMultimap The argument multimap used to find the prefix and its values.
     * @return A new Calorie
     * @throws ParseException if there is an incorrect value for the Calorie.
     */
    private static Calorie optionalPresentCaloriePrefix(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getValue(PREFIX_CALORIE).isPresent()) {
            return ParserUtil.parseCalorie(argumentMultimap.getValue(PREFIX_CALORIE).get());
        }
        return new Calorie("0000");
    }
}
