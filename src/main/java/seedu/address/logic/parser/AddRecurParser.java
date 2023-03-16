package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
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

import java.util.stream.Stream;

import seedu.address.logic.commands.AddLabCommand;
import seedu.address.logic.commands.AddRecurCommand;
import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Parses input arguments and creates a new AddRecur object
 */
public class AddRecurParser implements Parser<AddRecurCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecur
     * and returns an AddRecur object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecurCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECUR);

        //Make the user not create lab and students with the same command
        if (arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG) && (!arePrefixesPresent(argMultimap, PREFIX_RECUR)
                || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurCommand.MESSAGE_USAGE));
        }

        String name;
        if (isLab(args)) {
            name = ParserUtil.parseLabName(argMultimap.getValue(PREFIX_LAB).get());
            Lab lab = new Lab(name);
            return new AddRecurCommand(lab, true, false, false);
        } else {
            name = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());
            Tutorial tutorial = new Tutorial(name);
            return new AddRecurCommand(tutorial, false, true, false);
        }
        //When isConsultation is added, need to check for 3 conditions instead
        /*
        else if (isTutorial(args)) {
            name = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());
            Tutorial tutorial = new Tutorial(name);
            return new AddTutorialCommand(tutorial);
        }
        */
    }

    boolean isLab(String args) {
        return args.trim().split(" ")[0].equals("Lab");
    }

    boolean isTutorial(String args) {
        return args.trim().split(" ")[0].equals("Tutorial");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains command to add students (cannot add student and lab
     * using the same command.)
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
