package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Event;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_FILE, PREFIX_TUTORIAL,
                        PREFIX_CONSULTATION, PREFIX_LAB);

        Index index;
        boolean isTutorial;
        boolean isLab;
        boolean isConsultation;

        Event newEvent;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_TUTORIAL).isPresent()
                && !argMultimap.getValue(PREFIX_LAB).isPresent()
                && !argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            newEvent = new Tutorial(ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get()));
            isTutorial = true;
            isLab = false;
            isConsultation = false;
        } else if (argMultimap.getValue(PREFIX_LAB).isPresent()) {
            newEvent = new Lab(ParserUtil.parseLabName(argMultimap.getValue(PREFIX_LAB).get()));
            isTutorial = false;
            isLab = true;
            isConsultation = false;
        } else {
            newEvent = new Consultation(ParserUtil.parseLabName(argMultimap.getValue(PREFIX_CONSULTATION).get()));
            isTutorial = false;
            isLab = false;
            isConsultation = true;
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            newEvent.changeDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_FILE).isPresent()) {
            newEvent.addAttachment(ParserUtil.parseEventFile(argMultimap.getValue(PREFIX_FILE).get()));
        }
        return new EditEventCommand(index, newEvent, isTutorial, isLab, isConsultation);
    }
}
