package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERFORMANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHOTO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddRecurCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Parses input arguments and creates a new AddRecur object.
 */
public class AddRecurParser implements Parser<AddRecurCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecur
     * and returns an AddRecur object for execution.
     *
     * @param args            the string argument to be parsed.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddRecurCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECUR);
        //Make the user not create (lab or tutorial) and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurCommand.MESSAGE_USAGE_NO_STUDENT_PREFIX));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_RECUR) || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurCommand.MESSAGE_USAGE_MISSING_RECUR_PREFIX));
        }
        String recurDetails = " " + ParserUtil.parseRecurName(argMultimap.getValue(PREFIX_RECUR).get());
        return checkRecurDetails(recurDetails);
    }

    /**
     * Ensures the recur details which is the presence of the prefix tutorial or prefix lab or prefix consultation.
     *
     * @param recurDetails            the string of the recur details to be checked.
     * @return AddRecurCommand        the valid recur command.
     * @throws ParseException         if the recur details are incorrect.
     */
    public AddRecurCommand checkRecurDetails(String recurDetails) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeFirstPrefix(recurDetails,
                PREFIX_TUTORIAL, PREFIX_LAB, PREFIX_CONSULTATION);

        if ((!(arePrefixesPresent(argMultimap, PREFIX_LAB)
                || arePrefixesPresent(argMultimap, PREFIX_CONSULTATION)
                || arePrefixesPresent(argMultimap, PREFIX_TUTORIAL))
                || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurCommand.MESSAGE_USAGE_MISSING_EVENT_PREFIX));
        }

        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            return parseEvent(recurDetails, PREFIX_TUTORIAL);
        } else if (argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            return parseEvent(recurDetails, PREFIX_CONSULTATION);
        } else {
            //If it is not tutorial and not a consultation, it has to be a lab
            return parseEvent(recurDetails, PREFIX_LAB);
        }
    }

    /**
     * Checks to if the recurring event is a lab or tutorial or consultation.
     * Do a typecasting for the respective events.
     *
     * @param newArgs           the modified string containing the arguments.
     * @param prefix            the prefix which is either Tutorial or Lab or Consultation.
     * @return AddRecurCommand  the valid add recur command.
     * @throws ParseException   if the parsed string is invalid.
     */
    public AddRecurCommand parseEvent(String newArgs, Prefix prefix) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(newArgs, prefix, PREFIX_COUNT, PREFIX_DATE);
        //Make the user not create (lab or tutorial) and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRecurCommand.MESSAGE_USAGE_NO_STUDENT_PREFIX));
        }
        int count = ParserUtil.parseRecurCount(argMultimap.getValue(PREFIX_COUNT).get());

        String name = ParserUtil.parseEventName(argMultimap.getValue(prefix).get());

        LocalDateTime date = LocalDateTime.now();

        //Lab is 2 hours long, Tutorial and Consultation is 1 hour long according to CS2040 Timetables
        if (argMultimap.getValue(PREFIX_DATE).isPresent() && prefix.equals(PREFIX_LAB)) {
            date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get(), 2);
        } else if (argMultimap.getValue(PREFIX_DATE).isPresent() && prefix.equals(PREFIX_TUTORIAL)) {
            date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get(), 1);
        } else if (argMultimap.getValue(PREFIX_DATE).isPresent() && prefix.equals(PREFIX_CONSULTATION)) {
            date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get(), 1);
        }

        if (prefix.equals(PREFIX_LAB)) {
            Lab lab = new Lab(name);
            lab.changeDate(date);
            return new AddRecurCommand(lab, true, false, false, count);
        } else if (prefix.equals(PREFIX_TUTORIAL)) {
            Tutorial tutorial = new Tutorial(name);
            tutorial.changeDate(date);
            return new AddRecurCommand(tutorial, false, true, false, count);
        } else {
            Consultation consultation = new Consultation(name);
            consultation.changeDate(date);
            return new AddRecurCommand(consultation, false, false, true, count);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap     the argument mappings to be checked.
     * @param prefixes             all the prefix to be checked.
     * @return                     if the prefixes are present.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap     the argument mappings to be checked.
     * @param prefixes             all the prefix to be checked.
     * @return                     if the prefixes are absent.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
