package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERFORMANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHOTO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.io.File;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Note;
import seedu.address.model.event.Tutorial;

/**
 * Parses input arguments and creates a new AddTutorial object.
 */
public class AddTutorialParser implements Parser<AddTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorial
     * and returns an AddTutorial object for execution.
     *
     * @param args            the string argument to be parsed.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddTutorialCommand parse(String args) throws ParseException {
        //newArgs to trim first word when more commands added to switch-case in AddressBookParser
        String newArgs = args.trim().replaceFirst("Tutorial", "");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL, PREFIX_DATE, PREFIX_FILE, PREFIX_NOTE);

        //Make the user not create tutorial and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL) || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        }
        String name = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());
        LocalDateTime date;
        File file;
        String note;
        Tutorial tutorial = new Tutorial(name);
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get(), 1);
            tutorial.changeDate(date);
        }

        //Checks for date availability when no date prefix is stated
        if (!argMultimap.getValue(PREFIX_DATE).isPresent()) {
            if (ParserUtil.isBusy(new LocalDateTime[]{tutorial.getDate(), tutorial.getDate().plusHours(1)})) {
                throw new ParseException("You are already busy during that period");
            } else {
                ParserUtil.makeBusy(new LocalDateTime[]{tutorial.getDate(), tutorial.getDate().plusHours(1)});
            }
        }

        if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
            file = ParserUtil.parseEventFile(argMultimap.getValue(PREFIX_FILE).get());
            tutorial.addAttachment(file);
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            note = ParserUtil.parseEventNote(argMultimap.getValue(PREFIX_NOTE).get());
            if (note.length() > Note.LENGTH_LIMIT) {
                throw new ParseException(String.format("Note is too long! Shrink it to under %1$s", Note.LENGTH_LIMIT));
            }
            tutorial.addNote(new Note(note));
        }
        return new AddTutorialCommand(tutorial);
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
