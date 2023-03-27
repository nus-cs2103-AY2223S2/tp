package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import arb.commons.core.index.Index;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.logic.parser.ArgumentMultimap;
import arb.logic.parser.ArgumentTokenizer;
import arb.logic.parser.Parser;
import arb.logic.parser.ParserUtil;
import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Name;
import arb.model.tag.Tag;


/**
 * Parses input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditProjectCommand
     * and returns an EditProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_PRICE, PREFIX_TAG,
                PREFIX_CLIENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE), pe);
        }

        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editProjectDescriptor.setTitle(ParserUtil.parseTitle(argumentMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argumentMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argumentMultimap.getValue(PREFIX_DEADLINE)
                                                                                        .get()));
        }

        if (argumentMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editProjectDescriptor.setPrice(ParserUtil.parsePrice(argumentMultimap.getValue(PREFIX_PRICE)
                    .get()));
        }

        if (argumentMultimap.getValue(PREFIX_CLIENT).isPresent()) {
            // throw exception if not valid name
            String clientName = argumentMultimap.getValue(PREFIX_CLIENT).get();
            if (!clientName.isBlank() && !Name.isValidName(clientName)) {
                throw new ParseException("Provided client name is not valid");
            }
            editProjectDescriptor.setClient(clientName);
        }

        parseTagsForEdit(argumentMultimap.getAllValues(PREFIX_TAG)).ifPresent(editProjectDescriptor::setTags);

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProjectCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProjectCommand(index, editProjectDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
