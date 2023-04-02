package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.model.util.SampleDataUtil.EMPTY_INPUT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.Remark;
import seedu.address.model.module.Resource;
import seedu.address.model.module.Teacher;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_TIMESLOT, PREFIX_ADDRESS,
                        PREFIX_REMARK, PREFIX_DEADLINE, PREFIX_TEACHER, PREFIX_RESOURCE);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        // Compulsory fields.
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)); // Lecture/Tutorial


        // Optional fields. Hence, we use the Optional.orElse() to handle the case when the field does not have a value.
        Teacher teacher = ParserUtil.parseTeacher(argMultimap.getValue(PREFIX_TEACHER).orElse(EMPTY_INPUT));
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).orElse(EMPTY_INPUT));
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(EMPTY_INPUT));
        Resource resource = ParserUtil.parseResource(argMultimap.getValue(PREFIX_RESOURCE).orElse(EMPTY_INPUT));
        TimeSlot timeSlot = ParserUtil.parseTimeSlot(argMultimap.getValue(PREFIX_TIMESLOT).orElse(EMPTY_INPUT));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(EMPTY_INPUT));

        Module module = new Module(name, resource, timeSlot, address, tagList, remark, deadline, teacher);

        return new AddCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
