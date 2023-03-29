package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
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

import seedu.address.logic.commands.AddConsultationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Note;

/**
 * Parses input arguments and creates a new AddConsultation object
 */
public class AddConsultationParser implements Parser<AddConsultationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddConsultation
     * and returns an AddConsultation object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConsultationCommand parse(String args) throws ParseException {
        //newArgs to trim first word when more commands added to switch-case in AddressBookParser
        String newArgs = args.trim().replaceFirst("Consultation", "");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONSULTATION);
        //Make the user not create lab and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultationCommand.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_CONSULTATION) || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultationCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseConsultationName(argMultimap.getValue(PREFIX_CONSULTATION).get());
        LocalDateTime date;
        File file;
        String note;
        Consultation consultation = new Consultation(name);
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
            consultation.changeDate(date);
        }
        if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
            file = ParserUtil.parseEventFile(argMultimap.getValue(PREFIX_FILE).get());
            consultation.addAttachment(file);
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            note = ParserUtil.parseEventNote(argMultimap.getValue(PREFIX_NOTE).get());
            consultation.addNote(new Note(note));
        }
        return new AddConsultationCommand(consultation);
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
