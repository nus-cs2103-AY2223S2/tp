package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_SET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_EVENT_SET);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        Optional<Set<Index>> eventIndexSet = parseIndexForEdit(argMultimap.getAllValues(PREFIX_EVENT_SET));
        parseEventForEdit(argMultimap.getAllValues(PREFIX_EVENT_SET)).ifPresent(editPersonDescriptor::setEventSet);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor, eventIndexSet);
    }

    /**
     * Parses {@code Collection<String> index} into a {@code Set<Index>} if {@code index} is non-empty.
     * If {@code index} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero index.
     */
    private Optional<Set<Index>> parseIndexForEdit(Collection<String> index) throws ParseException {
        assert index != null;

        if (index.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = index.size() == 1 && index.contains("") ? Collections.emptySet() : index;
        return Optional.of(ParserUtil.parseEventIndexTags(tagSet));
    }

    /**
     * Serves as a function to check whether prefix_event_tag is called
     */
    private Optional<Set<Event>> parseEventForEdit(Collection<String> event) {
        assert event != null;

        if (event.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = event.size() == 1 && event.contains("") ? Collections.emptySet() : event;
        return Optional.of(new HashSet<>());
    }
}
