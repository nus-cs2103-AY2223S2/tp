package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Lesson;
import seedu.address.model.tutee.fields.Remark;

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
                    PREFIX_SUBJECT, PREFIX_SCHEDULE, PREFIX_STARTTIME, PREFIX_ENDTIME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SUBJECT,
                PREFIX_SCHEDULE, PREFIX_STARTTIME, PREFIX_ENDTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        TuteeBuilder builder = new TuteeBuilder();
        builder.withName(
            ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get())
        )
        .withPhone(
            ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get())
        )
        .withEmail(
            ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())
        )
        .withAddress(
            ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())
        )
        // add command does not allow adding remarks straight away
        .withRemark(new Remark(""))
        .withSubject(
            ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get())
        )
        .withSchedule(
            ParserUtil.parseSchedule(argMultimap.getValue(PREFIX_SCHEDULE).get())
        )
        .withStartTime(
            ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get())
        )
        .withEndTime(
            ParserUtil.parseEndTime(
                argMultimap.getValue(PREFIX_ENDTIME).get(),
                argMultimap.getValue(PREFIX_STARTTIME).get()
            )
        )
        .withTags(
            ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG))
        ).withAttendance(new Attendance())
        .withLessons(new Lesson());

        Tutee tutee = builder.build();

        return new AddCommand(tutee);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
