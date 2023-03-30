package seedu.quickcontacts.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.EditMeetingsCommand;
import seedu.quickcontacts.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingsCommand object
 */
public class EditMeetingParser implements Parser<EditMeetingsCommand> {
    public static final Prefix[] PREFIXES = {PREFIX_MEETING_TITLE, PREFIX_DATETIME, PREFIX_PERSON,
        PREFIX_LOCATION, PREFIX_DESCRIPTION};

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingsCommand
     * and returns an EditMeetingsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIXES);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                    EditMeetingsCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_MEETING_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MEETING_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editMeetingDescriptor.setDateTime(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            editMeetingDescriptor.setAttendees(ParserUtil.parseAttendees(argMultimap.getAllValues(PREFIX_PERSON)));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editMeetingDescriptor.setDescription(
                ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }


        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingsCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingsCommand(index, editMeetingDescriptor);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String args) {
        String[] argsSplit = args.split(" ");
        if (args.isEmpty()) {
            return new AutocompleteResult(PREFIXES[0], false);
        }
        String lastPrefix = argsSplit[argsSplit.length - 1];

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + lastPrefix, PREFIXES);
        boolean returnNext = false;

        for (Prefix p : PREFIXES) {
            if (returnNext) {
                return new AutocompleteResult(p, true);
            }

            if (argMultimap.getValue(p).isPresent() && p.toString().equals(lastPrefix)) {
                returnNext = true;
            }
        }

        return new AutocompleteResult(PREFIXES[0], true);
    }
}
